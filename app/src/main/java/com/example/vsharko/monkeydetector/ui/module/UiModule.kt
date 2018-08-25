package com.example.vsharko.monkeydetector.ui.module

import com.example.vsharko.monkeydetector.networking.helper.NetworkingHelper
import com.example.vsharko.monkeydetector.scopes.PerActivity
import com.example.vsharko.monkeydetector.ui.presenter.MainPresenter
import com.example.vsharko.monkeydetector.ui.presenter.MainPresenterImpl
import com.example.vsharko.monkeydetector.ui.view.MainActivityView
import dagger.Module
import dagger.Provides

@Module
class UiModule constructor(var view: MainActivityView){
    @PerActivity
    @Provides
    fun providePresenter(helper : NetworkingHelper) : MainPresenter {
        return MainPresenterImpl(view,helper)
    }
}