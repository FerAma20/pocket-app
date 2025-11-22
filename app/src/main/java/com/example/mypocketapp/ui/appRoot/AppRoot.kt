package com.example.mypocketapp.ui.appRoot

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import com.example.mypocketapp.ui.drawer.AppDrawer
import com.example.mypocketapp.ui.login.LoginViewModel
import com.example.mypocketapp.ui.navigation.AppNavHost
import com.example.mypocketapp.ui.navigation.DrawerDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot(
    viewModel: LoginViewModel
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Estado de la opción seleccionada en el drawer
    var selectedDestination by remember {
        mutableStateOf<DrawerDestination>(DrawerDestination.Settings)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                selectedDestination = selectedDestination,
                onDestinationClicked = { destination ->
                    selectedDestination = destination
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch { drawerState.close() }
                },
                onSettingsClicked = {
                    selectedDestination = DrawerDestination.Settings
                    navController.navigate(DrawerDestination.Settings.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch { drawerState.close() }
                },
                onLogoutClicked = {
                    viewModel.logout()
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(selectedDestination.title) },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open()
                                    else drawerState.close()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir menú"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier
                    .padding(innerPadding)   // ← usas innerPadding
                    .windowInsetsPadding(WindowInsets().only(WindowInsetsSides.Top))
            )
        }

    }
}
