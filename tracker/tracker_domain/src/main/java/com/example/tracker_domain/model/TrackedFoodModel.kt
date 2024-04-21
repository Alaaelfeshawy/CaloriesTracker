package com.example.tracker_domain.model

import java.time.LocalDate

data class TrackedFoodModel(
    var name: String?,
    var carbs: Int,
    var protein: Int,
    var fat: Int,
    var imageUrl: String?,
    var type: MealType,
    var amount: Int,
    var date : LocalDate,
    var calories: Int,
    var id: Int? = null
)
