package com.example.mypocketapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.mypocketapp.ui.navigation.AppNavHost
import com.example.mypocketapp.ui.theme.MyPocketAppTheme
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var retrofit: Retrofit
    @Inject @Named("baseUrl") lateinit var baseUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SmokeTest", "Retrofit baseUrl = $baseUrl")
        setContent {
            MyPocketAppTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}

