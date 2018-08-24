package com.example.vsharko.monkeydetector.model

data class PredictionX(
        val probability: Double,
        val tagId: String,
        val tagName: String
)