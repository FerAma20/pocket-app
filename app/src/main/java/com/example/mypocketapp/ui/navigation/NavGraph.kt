package com.example.mypocketapp.ui.navigation

// NavGraph.kt
// ui/navigation/NavGraph.kt

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mypocketapp.ui.home.HomeScreen
import com.example.mypocketapp.ui.login.LoginScreen
import com.example.mypocketapp.ui.login.LoginViewModel

object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Routes.LOGIN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.LOGIN) {
            // inyecta automáticamente el ViewModel desde el grafo de Hilt
            val vm: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel = vm,
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen( )
        }
    }
}

