package com.example.vsharko.monkeydetector.utils

import android.view.View

class utils {

    companion object {
        public fun visibilityChanger(views : List<View>){
            for(view in views){
                view.visibility = if (view.visibility == View.GONE)
                    View.VISIBLE
                else
                    View.GONE
            }
        }
    }

}