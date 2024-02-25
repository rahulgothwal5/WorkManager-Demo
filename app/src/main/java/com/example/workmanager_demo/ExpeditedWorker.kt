package com.example.workmanager_demo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.workmanager_demo.useCase.GetTweetsForTopicUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first


@HiltWorker
class ExpeditedWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val getTweetsForTopicUseCase: GetTweetsForTopicUseCase,
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        return try {
            val tweetsResult = getTweetsForTopicUseCase("Android Development").first()
            delay(10000)

            if (tweetsResult is com.example.workmanager_demo.Result.Success) {
                // Handle successful result
                val tweets = tweetsResult
                // Process tweets...
                Result.success()
            } else {
                // Handle error result
                Result.failure()
            }
        } catch (e: Exception) {
            // Handle exception
            Result.failure()
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        val notification = createForegroundNotification()
        return ForegroundInfo(ONGOING_NOTIFICATION_ID, notification)
    }

    private fun createForegroundNotification(): Notification {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Fetching Tweets")
            .setContentText("Fetching tweets for the specified topic...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    companion object {
        private const val ONGOING_NOTIFICATION_ID = 123
        private const val CHANNEL_ID = "TweetsWorkerChannel"
    }
}
