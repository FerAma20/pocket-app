package com.example.mypocketapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.example.mypocketapp.data.local.SessionDataStore
import com.example.mypocketapp.ui.navigation.AppNavHost
import com.example.mypocketapp.ui.navigation.Routes
import com.example.mypocketapp.ui.theme.MyPocketAppTheme
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named
import androidx.compose.runtime.getValue
import com.example.mypocketapp.ui.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var retrofit: Retrofit
    @Inject @Named("baseUrl") lateinit var baseUrl: String
    @Inject lateinit var sessionStore: SessionDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SmokeTest", "Retrofit baseUrl = $baseUrl")
        val vm = MainViewModel()
        val result = vm.sum(7, 5)
        Log.d("JNI_TEST", "Resultado JNI: $result")
        setContent {
            val isLoggedIn by sessionStore.isLoggedInFlow.collectAsState(initial = false)
            MyPocketAppTheme {
                val navController = rememberNavController()
                AppNavHost(navController, if (isLoggedIn) Routes.HOME else Routes.LOGIN)
            }
        }
    }
}

