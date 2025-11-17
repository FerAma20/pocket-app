package com.example.mypocketapp.ui.login

// LoginViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypocketapp.data.repository.AuthRepository
import com.example.mypocketapp.data.repository.FakeAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repo: AuthRepository = FakeAuthRepository()
) : ViewModel() {

    private val _ui = MutableStateFlow(LoginUiState())
    val ui = _ui.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.CompanyChanged -> _ui.value = _ui.value.copy(
                idCompany = event.value,
                errorMessage = null
            )
            is LoginEvent.EmailChanged -> _ui.value = _ui.value.copy(
                email = event.value,
                errorMessage = null
            )
            is LoginEvent.PasswordChanged -> _ui.value = _ui.value.copy(
                password = event.value,
                errorMessage = null
            )
            LoginEvent.TogglePasswordVisibility -> _ui.value = _ui.value.copy(
                isPasswordVisible = !_ui.value.isPasswordVisible
            )
            LoginEvent.Submit -> submit()
        }
    }

    private fun submit() {
        val idCompany = _ui.value.idCompany.trim()
        val email = _ui.value.email.trim()
        val password = _ui.value.password

        // Validaciones simples
        if (idCompany.isEmpty()) {
            _ui.value = _ui.value.copy(errorMessage = "Ingresa el identificador de empresa")
            return
        }
        if (!email.contains("@")) {
            _ui.value = _ui.value.copy(errorMessage = "Email inválido")
            return
        }
        if (password.length < 6) {
            _ui.value = _ui.value.copy(errorMessage = "La contraseña debe tener al menos 6 caracteres")
            return
        }

        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, errorMessage = null)
            val result = repo.login(idCompany, email, password)
            _ui.value = if (result.isSuccess) {
                _ui.value.copy(isLoading = false, isLoggedIn = true)
            } else {
                _ui.value.copy(
                    isLoading = false,
                    errorMessage = result.exceptionOrNull()?.message ?: "Error desconocido"
                )
            }
        }
    }
}
