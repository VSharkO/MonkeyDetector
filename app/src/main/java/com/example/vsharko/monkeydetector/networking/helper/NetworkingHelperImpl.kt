package com.example.vsharko.monkeydetector.networking.helper

import com.example.vsharko.monkeydetector.networking.Service
import com.example.vsharko.monkeydetector.utils.NetworkResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkingHelperImpl(private val mService: Service) : NetworkingHelper {

    override fun getProductsFromAPI(listener: NetworkResponseListener<String>, search: String) {
        mService.getPrediction(search).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.body() != null) {
                    val data = response.body()
                    if (data != null)
                        listener.onSuccess(data)
                }

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                listener.onFailure(t)
            }
        })
    }
}