package com.example.mypocketapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen() {
    CenteredText("Dashboard")
}

@Composable
fun AccountsScreen() {
    CenteredText("Cuentas")
}

@Composable
fun ReportsScreen() {
    CenteredText("Reportes")
}

@Composable
fun NotificationsScreen() {
    CenteredText("Notificaciones")
}

@Composable
fun SettingsScreen() {
    CenteredText("Configuración")
}

@Composable
private fun CenteredText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}
