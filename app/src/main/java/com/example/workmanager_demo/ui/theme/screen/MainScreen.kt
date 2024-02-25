package com.example.workmanager_demo.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier.padding(20.dp), onClick = {
            navHostController.navigate(Screens.OneTimeWorkerScreen.screen)
        }) {
            Text(text = "One Time Work Request @Hilt")
        }
        Button(
            modifier = Modifier.padding(20.dp),
            onClick = {
                navHostController.navigate(Screens.ExpeditedWorkerScreen.screen)
            }) {
            Text(text = "Expedited Work Request")
        }
        Button(
            modifier = Modifier.padding(20.dp),
            onClick = { navHostController.navigate(Screens.PeriodicWorkerScreen.screen) }) {
            Text(text = "Periodic Work Request 3")
        }
    }
}
