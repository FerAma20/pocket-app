package com.example.mypocketapp.ui.login

// LoginScreen.kt
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    val ui = viewModel.ui.collectAsState()
    val focus = LocalFocusManager.current

    if (ui.value.isLoggedIn) {
        onLoginSuccess()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Iniciar sesión", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))

            // Empresa
            OutlinedTextField(
                value = ui.value.idCompany,
                onValueChange = { viewModel.onEvent(LoginEvent.CompanyChanged(it)) },
                label = { Text("Empresa (idCompany)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focus.moveFocus(FocusDirection.Down) }),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            // Email
            OutlinedTextField(
                value = ui.value.email,
                onValueChange = { viewModel.onEvent(LoginEvent.EmailChanged(it)) },
                label = { Text("Email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focus.moveFocus(FocusDirection.Down) }),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            // Password
            OutlinedTextField(
                value = ui.value.password,
                onValueChange = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = if (ui.value.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val text = if (ui.value.isPasswordVisible) "Ocultar" else "Mostrar"
                    TextButton(onClick = { viewModel.onEvent(LoginEvent.TogglePasswordVisibility) }) {
                        Text(text)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focus.clearFocus()
                    viewModel.onEvent(LoginEvent.Submit)
                }),
                modifier = Modifier.fillMaxWidth()
            )

            if (ui.value.errorMessage != null) {
                Spacer(Modifier.height(8.dp))
                Text(ui.value.errorMessage!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { viewModel.onEvent(LoginEvent.Submit) },
                enabled = !ui.value.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (ui.value.isLoading) {
                    CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Ingresando…")
                } else {
                    Text("Ingresar")
                }
            }
        }
    }
}
