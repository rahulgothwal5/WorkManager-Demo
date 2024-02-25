package com.example.workmanager_demo

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanager_demo.useCase.GetTweetsForTopicUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class OneTimeWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val getTweetsForTopicUseCase: GetTweetsForTopicUseCase,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        return try {
            val tweetsResult = getTweetsForTopicUseCase("Android Development").first()

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



}