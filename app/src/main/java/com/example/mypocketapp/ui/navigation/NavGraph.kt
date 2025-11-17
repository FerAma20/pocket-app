package com.example.mypocketapp.ui.navigation

// NavGraph.kt
import androidx.compose.runtime.Composable
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
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            val vm = androidx.lifecycle.viewmodel.compose.viewModel<LoginViewModel>()
            LoginScreen(viewModel = vm) {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                }
            }
        }
        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}

