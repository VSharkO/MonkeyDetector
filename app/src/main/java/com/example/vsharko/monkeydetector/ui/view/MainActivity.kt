package com.example.vsharko.monkeydetector.ui.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

    override fun setResultText(result : String , probability : Double){
        var resultSplit = result.split("_")
        resultTextSpacious.text = "${resultSplit[0].capitalize()} ${resultSplit[1]}"
        probabilityText.text = getString(R.string.probability)
                .plus(" "+"%.2f".format(probability*100) + "%")
    }

    private fun setOnclickListeners(){
        findButton.setOnClickListener{setPicture()}
        goButton.setOnClickListener{ presenter.getPredictions(editTextImageUrl.text.toString())
                                        setPicture() }

        tryAgainButton.setOnClickListener {changeVisibilityViews()}
    }

    override fun changeVisibilityViews(){
        val views: List<View> = mutableListOf(editTextImageUrl,textViewOr,textViewImageUrl,
                textViewImportLocalImage,findButton,importButton,goButton,
                resultText,resultTextSpacious,probabilityText,tryAgainButton)
        changeViewsVisibility(views)
    }

    private fun changeViewsVisibility(views : List<View>){
        for(view in views){
            view.visibility = if (view.visibility == View.GONE)
                View.VISIBLE
            else
                View.GONE
        }
    }

    override fun showFailureToast() {
        Toast.makeText(this,getString(R.string.failureToast),Toast.LENGTH_LONG).show()
    }
}
