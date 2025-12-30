package com.example.mypocketapp.ui.camera

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AppRoot() {
    var screen by remember { mutableStateOf<Screen>(Screen.Home) }

    when (screen) {
        Screen.Home -> HomeCameraScreen() { screen = Screen.Camera }
        Screen.Camera -> CameraScreen { screen = Screen.Home }
    }
}