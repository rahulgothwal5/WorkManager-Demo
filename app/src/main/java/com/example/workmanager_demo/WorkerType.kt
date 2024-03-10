package com.example.workmanager_demo

sealed class WorkerType{
    object PeriodicWorker:WorkerType()
    object OneTimeWorker:WorkerType()
    object ExpeditedWorker:WorkerType()
}
