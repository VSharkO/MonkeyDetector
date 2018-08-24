package com.example.vsharko.monkeydetector

import com.example.vsharko.monkeydetector.networking.helper.NetworkingHelper
import com.example.vsharko.monkeydetector.networking.networkingDI.NetworkModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun injectNetworkingHelper(): NetworkingHelper
}
