package com.example.mypocketapp.ui.drawer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mypocketapp.ui.navigation.DrawerDestination

@Composable
fun AppDrawer(
    selectedDestination: DrawerDestination,
    onDestinationClicked: (DrawerDestination) -> Unit,
    onSettingsClicked: () -> Unit,
    onLogoutClicked: () -> Unit
) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Top))
                .verticalScroll(rememberScrollState())
        ) {
            DrawerHeader(
                userName = "Fernando Escobar",
                userEmail = "fernando@correo.com"
            )

            Spacer(Modifier.height(12.dp))
            var temo = DrawerDestination.mainDestinations
            // Ítems principales
            DrawerDestination.mainDestinations.forEachIndexed { index, destination ->
                Log.d(
                    "DrawerDebug",
                    "index=$index, dest=${destination?.title}, route=${destination?.route}"
                )

                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    icon = { Icon(destination.icon, contentDescription = null) },
                    label = { Text(text = destination.title) },
                    selected = destination == selectedDestination,
                    onClick = { onDestinationClicked(destination) }
                )
            }


            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            // Empujar footer al fondo
            Spacer(modifier = Modifier.weight(1f))

            NavigationDrawerItem(
                modifier = Modifier.padding(horizontal = 16.dp),
                icon = { Icon(DrawerDestination.Settings.icon, contentDescription = null) },
                label = { Text(text = "Configuración") },
                selected = selectedDestination is DrawerDestination.Settings,
                onClick = onSettingsClicked
            )
            NavigationDrawerItem(
                modifier = Modifier.padding(horizontal = 16.dp),
                icon = { Icon(DrawerDestination.Settings.icon, contentDescription = null) },
                label = { Text(text = "Cerrar sesión") },
                selected = false,
                onClick = onLogoutClicked
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
