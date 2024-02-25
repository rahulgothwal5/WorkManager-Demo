package com.example.workmanager_demo.ui.theme.screen

sealed class Screens(val screen: String) {
    object MainScreen : Screens("mainscreen")
    object OneTimeWorkerScreen : Screens("onetimeworkerscreen")
    object ExpeditedWorkerScreen : Screens("expeditedworkerscreen")
    object PeriodicWorkerScreen : Screens("periodicworkerscreen")
}


