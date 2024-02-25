package com.example.workmanager_demo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workmanager_demo.ui.theme.WorkManagerDemoTheme
import com.example.workmanager_demo.ui.theme.screen.ExpeditedWorkerScreen
import com.example.workmanager_demo.ui.theme.screen.MainScreen
import com.example.workmanager_demo.ui.theme.screen.OneTimeWorkerScreen
import com.example.workmanager_demo.ui.theme.screen.PeriodicWorkerScreen
import com.example.workmanager_demo.ui.theme.screen.Screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkManagerDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
private fun App() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screens.MainScreen.screen) {
        composable(
            route = Screens.MainScreen.screen,
        ) {
            MainScreen(navController)
        }
        composable(
            route = Screens.OneTimeWorkerScreen.screen,
        ) {
            OneTimeWorkerScreen(navController)
        }
        composable(
            route = Screens.ExpeditedWorkerScreen.screen,
        ) {
            ExpeditedWorkerScreen(navController)
        }
        composable(
            route = Screens.PeriodicWorkerScreen.screen,
        ) {
            PeriodicWorkerScreen(navController)
        }
    }
}
