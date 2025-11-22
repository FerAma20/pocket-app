package com.example.mypocketapp.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mypocketapp.ui.screens.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DrawerDestination.Dashboard.route,
        modifier = modifier
    ) {
        composable(DrawerDestination.Dashboard.route) {
            DashboardScreen()
        }
        composable(DrawerDestination.Accounts.route) {
            AccountsScreen()
        }
        composable(DrawerDestination.Reports.route) {
            ReportsScreen()
        }
        composable(DrawerDestination.Notifications.route) {
            NotificationsScreen()
        }
        composable(DrawerDestination.Settings.route) {
            SettingsScreen()
        }
    }
}
