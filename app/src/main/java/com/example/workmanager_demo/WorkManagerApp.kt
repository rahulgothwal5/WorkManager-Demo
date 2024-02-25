package com.example.workmanager_demo

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.workmanager_demo.useCase.GetTweetsForTopicUseCase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WorkManagerApp : Application(), Configuration.Provider {

    @Inject
    lateinit var customOneTimeWorkerFactory: CustomOneTimeWorkerFactory

    private fun newWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(customOneTimeWorkerFactory)
            .build()
    }

    override val workManagerConfiguration: Configuration
        get() = newWorkManagerConfiguration()

}

class CustomOneTimeWorkerFactory @Inject constructor(private val getTweetsForTopicUseCase: GetTweetsForTopicUseCase) :
    WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker =
//        OneTimeWorker(appContext, workerParameters, getTweetsForTopicUseCase)
//        ExpeditedWorker(appContext, workerParameters, getTweetsForTopicUseCase)
        PeriodicWorker(appContext, workerParameters)
}