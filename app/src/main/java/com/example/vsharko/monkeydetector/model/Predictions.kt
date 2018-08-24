package com.example.vsharko.monkeydetector.model

data class Predictions(
        val id: String,
        val project: String,
        val iteration: String,
        val created: String,
        val predictions: List<PredictionX>
)