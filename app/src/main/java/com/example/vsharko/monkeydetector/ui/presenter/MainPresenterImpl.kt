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

    private val listener = object : NetworkResponseListener<Predictions> {
        override fun onSuccess(callback: Predictions) {
            val prediction = callback.predictions[0].tagName
            val probability = callback.predictions[0].probability
            view.setResultText(predictionFormatName(prediction),probability)
            view.changeVisibilityToCrucialViews()
        }

        override fun onFailure(throwable: Throwable) {
            Timber.e(throwable)
            view.showFailureToast()
        }
    }

    fun predictionFormatName(result : String) : String{
        val resultSplit = result.split("_")
        val sb = StringBuilder()

        if (resultSplit.size > 1){
            for (word in resultSplit) {
                sb.append(word)
                sb.append(" ")
            }
            sb.delete(sb.length-1,sb.length -1)
        }
        else
            sb.append(resultSplit)

        return sb.toString()
    }
}

