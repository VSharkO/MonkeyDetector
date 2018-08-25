package com.example.vsharko.monkeydetector.ui

import com.example.vsharko.monkeydetector.AppComponent
import com.example.vsharko.monkeydetector.scopes.PerActivity
import com.example.vsharko.monkeydetector.ui.module.UiModule
import com.example.vsharko.monkeydetector.ui.view.MainActivity
import dagger.Component

@PerActivity
@Component(modules = [UiModule::class], dependencies = [AppComponent::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}