package com.example.tracker_domain.model

data class ProductModel(
    var image: String?,
    var carbohydrates100g: Int,
    var energyKcal100g: Int,
    var fat100g: Int,
    var proteins100g: Int,
    var name: String?
)
