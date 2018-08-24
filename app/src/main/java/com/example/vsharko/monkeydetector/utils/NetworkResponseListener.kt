package com.example.vsharko.monkeydetector.utils


interface NetworkResponseListener<T> {
    fun onSuccess(callback: T)
    fun onFailure(throwable: Throwable)
}