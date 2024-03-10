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
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import com.example.workmanager_demo.ExpeditedWorker
import com.example.workmanager_demo.WorkManagerApp
import com.example.workmanager_demo.WorkerType
import java.time.Duration

@Composable
fun ExpeditedWorkerScreen(navController: NavHostController) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        WorkManagerApp.workerType = WorkerType.ExpeditedWorker
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier.padding(20.dp), onClick = {
            startExpeditedWorker(context)
        }) {
            Text(text = "Start Expedited Worker")
        }
    }
}

fun startExpeditedWorker(context: Context) {
    val workRequest = OneTimeWorkRequestBuilder<ExpeditedWorker>()
        .setExpedited(OutOfQuotaPolicy.DROP_WORK_REQUEST)
        .setBackoffCriteria(
            backoffPolicy = BackoffPolicy.LINEAR,
            duration = Duration.ofSeconds(15)
        ).build()
    WorkManager.getInstance(context).enqueue(workRequest)
}
