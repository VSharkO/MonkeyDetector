package com.example.vsharko.monkeydetector.networking

import com.example.vsharko.monkeydetector.model.Predictions
import retrofit2.Call
import retrofit2.http.*

interface Service {

    @FormUrlEncoded
    @POST("/")
    fun getPrediction(@Field("url") pictureUrl: String) : Call<Predictions>
}