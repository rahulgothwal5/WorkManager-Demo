package com.example.workmanager_demo.ui.theme.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanager_demo.PeriodicWorker
import com.example.workmanager_demo.WorkManagerApp.Companion.workerType
import com.example.workmanager_demo.WorkerType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.concurrent.TimeUnit

@Composable
fun PeriodicWorkerScreen(navController: NavHostController) {
    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        workerType = WorkerType.PeriodicWorker
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier.padding(20.dp), onClick = {
            scope.launch {
                startPeriodicWorker(context, lifeCycleOwner)
            }
        }) {
            Text(text = "Start Periodic Worker")
        }

    }


}

suspend fun startPeriodicWorker(context: Context, lifeCycleOwner: LifecycleOwner) {
    val constraints = Constraints.Builder().setRequiresBatteryNotLow(true).build()

    val workRequest = PeriodicWorkRequestBuilder<PeriodicWorker>(
        repeatInterval = 3,
        repeatIntervalTimeUnit = TimeUnit.MINUTES,
//        flexTimeInterval = 1,
//        flexTimeIntervalUnit = TimeUnit.MINUTES
    ).setBackoffCriteria(
        backoffPolicy = BackoffPolicy.LINEAR,
        duration = Duration.ofSeconds(10)
    ).setConstraints(constraints).build()
    val workManager = WorkManager.getInstance(context)
    workManager.enqueueUniquePeriodicWork(
        "newWork",
        ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
        workRequest
    )
    workManager.getWorkInfosForUniqueWorkLiveData("newWork").observe(
        lifeCycleOwner
    ) {
        it.forEach { workInfo ->
            Log.d("PERIODIC", "Current state of newWork is ${workInfo.state}")
        }
    }
    delay(10000)
    workManager.cancelAllWork()
}


