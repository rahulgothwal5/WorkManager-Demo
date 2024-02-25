package com.example.workmanager_demo

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanager_demo.useCase.GetTweetsForTopicUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class PeriodicWorker constructor(
    context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        delay(2000)
        val formattedTime = getCurrentTimeFormatted()
        Log.d("PERIODIC","Periodic worker ran at : $formattedTime")
        return Result.success()
    }

    private fun getCurrentTimeFormatted(): String {
        val currentTime = Instant.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault())
        return formatter.format(currentTime)
    }

}