package com.example.vsharko.monkeydetector.networking.helper

import com.example.vsharko.monkeydetector.utils.NetworkResponseListener

interface NetworkingHelper {
    fun getProductsFromAPI(listener: NetworkResponseListener<String>, search: String)
}