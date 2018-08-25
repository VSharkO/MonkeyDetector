package com.example.vsharko.monkeydetector.ui.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.vsharko.monkeydetector.App
import com.example.vsharko.monkeydetector.R
import com.example.vsharko.monkeydetector.ui.DaggerMainComponent
import com.example.vsharko.monkeydetector.ui.module.UiModule
import com.example.vsharko.monkeydetector.ui.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainComponent.builder().appComponent(App().component)
                .uiModule(UiModule(this))
                .build()
                .inject(this)

        setOnclickListeners()
    }

    private fun setPicture(){
        Glide.with(imageView)
                .load(editTextImageUrl.text.toString())
                .into(imageView)
    }

    override fun setResultText(result : String){
        textViewOr.text = result
    }

    private fun setOnclickListeners(){
        findButton.setOnClickListener{setPicture()}
        goButton.setOnClickListener{presenter.getPredictions(editTextImageUrl.text.toString())}
    }

}
