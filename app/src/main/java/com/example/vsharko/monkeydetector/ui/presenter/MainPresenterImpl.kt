package com.example.vsharko.monkeydetector.ui.presenter

import com.example.vsharko.monkeydetector.model.Predictions
import com.example.vsharko.monkeydetector.networking.helper.NetworkingHelper
import com.example.vsharko.monkeydetector.ui.view.MainActivityView
import com.example.vsharko.monkeydetector.utils.NetworkResponseListener
import timber.log.Timber

class MainPresenterImpl constructor(view: MainActivityView,private val helper: NetworkingHelper) : MainPresenter {

    override fun getPredictions(search: String) {
        helper.getPredictionsFromAPI(listener,search)
    }

    var listener = object : NetworkResponseListener<Predictions> {
        override fun onSuccess(callback: Predictions) {
           view.setResultText(callback.predictions[0].tagName)
        }

        override fun onFailure(throwable: Throwable) {
            Timber.e(throwable)
        }
    }
}