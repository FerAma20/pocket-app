package com.example.mypocketapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerDestination(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Dashboard : DrawerDestination(
        route = "dashboard",
        title = "Dashboard",
        icon = Icons.Filled.Home
    )

    object Accounts : DrawerDestination(
        route = "accounts",
        title = "Cuentas",
        icon = Icons.Filled.AccountBox
    )

    object Reports : DrawerDestination(
        route = "reports",
        title = "Reportes",
        icon = Icons.Filled.BarChart
    )

    object Camera : DrawerDestination(
        route = "camera",
        title = "Camara",
        icon = Icons.Filled.Camera
    )

    object Notifications : DrawerDestination(
        route = "notifications",
        title = "Notificaciones",
        icon = Icons.Filled.Notifications
    )

    object Settings : DrawerDestination(
        route = "settings",
        title = "Configuración",
        icon = Icons.Filled.Settings
    )

    companion object {
        // IMPORTANTE: tipo no-nullable
        val mainDestinations: List<DrawerDestination> = listOf(
            Dashboard,
            Accounts,
            Reports,
            Camera,
            Notifications
        )
    }
}
