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
import com.example.vsharko.monkeydetector.utils.utils
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {
    override fun changeVisibilityToCrucialViews() {
        val views: List<View> = mutableListOf(editTextImageUrl, textViewImageUrl, findButton, goButton,
                resultText, resultTextSpacious, tryAgainButton)
        utils.visibilityChanger(views)
    }

    override fun showFailureToast() {
        Toast.makeText(this, getString(R.string.failureToast), Toast.LENGTH_SHORT).show()
    }


    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainComponent.builder().appComponent(App().component)
                .uiModule(UiModule(this))
                .build()
                .inject(this)

        setListeners()
    }

    private fun setPicture() {
        Glide.with(imageView)
                .load(editTextImageUrl.text.toString())
                .into(imageView)
    }

    override fun setResultText(result: String, probability: Double) {
        val stringProbability = getString(R.string.probability)
                .plus(" " + "%.2f".format(probability * 100) + "%")

        resultTextSpacious.text = result.capitalize()
    }

    private fun setListeners() {
        findButton.setOnClickListener { setPicture() }
        goButton.setOnClickListener {
            presenter.getPredictions(editTextImageUrl.text.toString())
            setPicture()
        }

        tryAgainButton.setOnClickListener { changeVisibilityToCrucialViews() }
    }
}
