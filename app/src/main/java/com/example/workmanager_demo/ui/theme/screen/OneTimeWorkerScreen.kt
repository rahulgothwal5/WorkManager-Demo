package com.example.workmanager_demo.ui.theme.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanager_demo.OneTimeWorker
import com.example.workmanager_demo.WorkManagerApp
import com.example.workmanager_demo.WorkerType
import java.time.Duration

@Composable
fun OneTimeWorkerScreen(navController: NavHostController) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        WorkManagerApp.workerType = WorkerType.OneTimeWorker
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier.padding(20.dp), onClick = {
            startOneTimeWorker(context)
        }) {
            Text(text = "Start One Time Worker")
        }

    }


}

fun startOneTimeWorker(context: Context) {
    val workRequest = OneTimeWorkRequestBuilder<OneTimeWorker>()
        .setInitialDelay(Duration.ofSeconds(10))
        .setBackoffCriteria(
            backoffPolicy = BackoffPolicy.LINEAR,
            duration = Duration.ofSeconds(10)
        ).build()
    WorkManager.getInstance(context).enqueue(workRequest)
}


