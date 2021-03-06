package com.example.vsharko.monkeydetector.ui.view

interface MainActivityView {
    fun setResultText(result: String, probability: Double)
    fun changeVisibilityToCrucialViews()
    fun showFailureToast()
}