package com.example.vsharko.monkeydetector.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.vsharko.monkeydetector.App
import com.example.vsharko.monkeydetector.DaggerAppComponent
import com.example.vsharko.monkeydetector.R
import com.example.vsharko.monkeydetector.model.Predictions
import com.example.vsharko.monkeydetector.networking.helper.NetworkingHelper
import com.example.vsharko.monkeydetector.utils.NetworkResponseListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var helper : NetworkingHelper
    lateinit var listener : NetworkResponseListener<Predictions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helper = DaggerAppComponent.builder().build().injectNetworkingHelper()
        initListener()
        findButton.setOnClickListener{Glide.with(imageView)
                    .load(editTextImageUrl.text.toString())
                    .into(imageView)
        }
        goButton.setOnClickListener{helper.getProductsFromAPI(listener,editTextImageUrl.text.toString())}
    }

    fun initListener(){
        listener = object : NetworkResponseListener<Predictions>{
            override fun onSuccess(callback: Predictions) {
                textViewOr.text = callback.predictions.get(0).tagName
            }

            override fun onFailure(throwable: Throwable) {
                Timber.e(throwable)
            }
        }
    }
}
