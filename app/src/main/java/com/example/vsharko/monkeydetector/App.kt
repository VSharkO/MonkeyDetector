package com.example.vsharko.monkeydetector

import android.app.Application
import timber.log.Timber
import javax.inject.Inject

class App : Application() {

    lateinit var instance: App
    lateinit var component: AppComponent

        override fun onCreate() {
            super.onCreate()
            instance = this
            component = DaggerAppComponent.create()
            Timber.plant(Timber.DebugTree())
        }
}
