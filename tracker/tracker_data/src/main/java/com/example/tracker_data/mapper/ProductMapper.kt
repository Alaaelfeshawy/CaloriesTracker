package com.example.tracker_data.mapper

import com.example.tracker_data.remote.dto.Product
import com.example.tracker_domain.model.ProductModel
import kotlin.math.roundToInt

fun Product.toProductModel() : ProductModel{
    return ProductModel(
        image = imageFrontThumbUrl,
        name = productName,
        carbohydrates100g = nutriments.carbohydrates100g.roundToInt(),
        proteins100g = nutriments.proteins100g.roundToInt(),
        fat100g = nutriments.fat100g.roundToInt(),
        energyKcal100g = nutriments.energyKcal100g.roundToInt()
    )
}