package com.example.vsharko.monkeydetector.networking.helper

import com.example.vsharko.monkeydetector.model.Predictions
import com.example.vsharko.monkeydetector.utils.NetworkResponseListener

interface NetworkingHelper {
    fun getPredictionsFromAPI(listener: NetworkResponseListener<Predictions>, search: String)
}