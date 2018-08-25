package com.example.vsharko.monkeydetector

import android.app.Application
import timber.log.Timber
import javax.inject.Inject

class App : Application() {

    val instance: App = this
    val component: AppComponent = DaggerAppComponent.create()

    override fun onCreate() {
            super.onCreate()
            Timber.plant(Timber.DebugTree())
        }
}
