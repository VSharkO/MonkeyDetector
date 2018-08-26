package com.example.vsharko.monkeydetector.networking.helper

import com.example.vsharko.monkeydetector.model.Predictions
import com.example.vsharko.monkeydetector.networking.Service
import com.example.vsharko.monkeydetector.utils.NetworkResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkingHelperImpl(private val mService: Service) : NetworkingHelper {

    override fun getPredictionsFromAPI(listener: NetworkResponseListener<Predictions>, search: String) {
        mService.getPrediction(search).enqueue(object : Callback<Predictions> {
            override fun onResponse(call: Call<Predictions>, response: Response<Predictions>) {
                if (response.body() != null) {
                    val data = response.body()
                    if (data != null)
                        listener.onSuccess(data)
                }else if(!response.isSuccessful){
                    onFailure(call,Throwable("bad request"))
                }
            }

            override fun onFailure(call: Call<Predictions>, t: Throwable) {
                listener.onFailure(t)
            }
        })
    }
}