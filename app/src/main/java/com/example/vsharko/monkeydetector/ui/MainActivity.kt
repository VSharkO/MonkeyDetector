package com.example.vsharko.monkeydetector.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.vsharko.monkeydetector.App
import com.example.vsharko.monkeydetector.DaggerAppComponent
import com.example.vsharko.monkeydetector.R
import com.example.vsharko.monkeydetector.networking.helper.NetworkingHelper
import com.example.vsharko.monkeydetector.utils.NetworkResponseListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var helper : NetworkingHelper
    lateinit var listener : NetworkResponseListener<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helper = DaggerAppComponent.builder().build().injectNetworkingHelper()
        initListener()
        findButton.setOnClickListener{helper.getProductsFromAPI(listener,editTextImageUrl.text.toString())}
    }

    fun initListener(){
        listener = object : NetworkResponseListener<String>{
            override fun onSuccess(callback: String) {
                textViewOr.text = callback
            }

            override fun onFailure(throwable: Throwable) {
                Timber.e(throwable)
            }
        }
    }
}
