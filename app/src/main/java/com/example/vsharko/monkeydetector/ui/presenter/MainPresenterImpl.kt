package com.example.vsharko.monkeydetector.ui.presenter

import android.graphics.*
import com.example.vsharko.monkeydetector.model.Predictions
import com.example.vsharko.monkeydetector.networking.helper.NetworkingHelper
import com.example.vsharko.monkeydetector.ui.view.MainActivityView
import com.example.vsharko.monkeydetector.utils.NetworkResponseListener
import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection


class MainPresenterImpl constructor(view: MainActivityView,private val helper: NetworkingHelper) : MainPresenter {

    var bmp : Bitmap? = null

    override fun getPredictions(search: String) {
//
        helper.getPredictionsFromAPI(listener,search)
    }

    private val listener = object : NetworkResponseListener<Predictions> {
        override fun onSuccess(callback: Predictions) {
            val prediction = callback.predictions[0].tagName
            val probability = callback.predictions[0].probability
            view.setResultText(predictionFormatName(prediction),probability)
            view.changeVisibilityToCrucialViews()
        }

        override fun onFailure(throwable: Throwable) {
            Timber.e(throwable)
            view.showFailureToast()
        }
    }

    fun predictionFormatName(result : String) : String{
        val resultSplit = result.split("_")
        val sb = StringBuilder()

        if (resultSplit.size > 1){
            for (word in resultSplit) {
                sb.append(word)
                sb.append(" ")
            }
            sb.delete(sb.length-1,sb.length -1)
        }
        else
            sb.append(resultSplit)

        return sb.toString()
    }

    fun doGreyscale(src: Bitmap?): Bitmap {
        // constant factors
        val GS_RED = 0.299
        val GS_GREEN = 0.587
        val GS_BLUE = 0.114

        // create output bitmap
        val bmOut = Bitmap.createBitmap(src!!.width, src.height, src.config)
        // pixel information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // get image size
        val width = src!!.width
        val height = src.height

        // scan through every single pixel
        for (x in 0 until width) {
            for (y in 0 until height) {
                // get one pixel color
                pixel = src.getPixel(x, y)
                // retrieve color of all channels
                A = Color.alpha(pixel)
                R = Color.red(pixel)
                G = Color.green(pixel)
                B = Color.blue(pixel)
                // take conversion up to one single value
                B = (GS_RED * R + GS_GREEN * G + GS_BLUE * B).toInt()
                G = B
                R = G
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B))
            }
        }

        // return final image
        return bmOut
    }

    fun getBitmapFromURL(src: String): Bitmap? {
        try {
            val url = java.net.URL(src)
            val connection = url
                    .openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input = connection.getInputStream()
            return BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    fun getResizedBitmap(bm: Bitmap?, newHeight: Int, newWidth: Int): Bitmap {
        val width = bm!!.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // CREATE A MATRIX FOR THE MANIPULATION
        val matrix = Matrix()
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight)

        // "RECREATE" THE NEW BITMAP

        return Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false)
    }
}

