package com.example.mypocketapp.ui.login

// LoginScreen.kt
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoggedIn: () -> Unit
) {
    val ui by viewModel.ui.collectAsState()
    val focus = LocalFocusManager.current

    // Navegación al lograr login
    LaunchedEffect(ui.isLoggedIn) {
        if (ui.isLoggedIn) onLoggedIn()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Iniciar sesión", style = MaterialTheme.typography.headlineMedium)

            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = ui.email,
                onValueChange = { viewModel.onEvent(LoginEvent.EmailChanged(it)) },
                label = { Text("Email") },
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.VisibilityOff, contentDescription = null) }, // placeholder icon
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focus.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = ui.password,
                onValueChange = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = if (ui.isPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (ui.isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = { viewModel.onEvent(LoginEvent.TogglePasswordVisibility) }) {
                        Icon(icon, contentDescription = "Mostrar/Ocultar contraseña")
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focus.clearFocus()
                        viewModel.onEvent(LoginEvent.Submit)
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )

            if (ui.errorMessage != null) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = ui.errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { viewModel.onEvent(LoginEvent.Submit) },
                enabled = !ui.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (ui.isLoading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp)
                    )
                    Text("Ingresando…")
                } else {
                    Text("Ingresar")
                }
            }
        }
    }
}
