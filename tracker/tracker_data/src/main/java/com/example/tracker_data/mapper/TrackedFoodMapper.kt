package com.example.tracker_data.mapper

import com.example.tracker_data.local.entity.TrackedFoodEntity
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFoodModel
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFoodModel() : TrackedFoodModel {
    return TrackedFoodModel(
        name=name,
        carbs=carbs,
        protein=protein,
        fat=fat,
        imageUrl=imageUrl,
        type= MealType.fromSting(type),
        amount=amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories=calories,
        id=id
    )
}

fun TrackedFoodModel.toTrackedFoodEntity() : TrackedFoodEntity {
    return TrackedFoodEntity(
        name=name,
        carbs=carbs,
        protein=protein,
        fat=fat,
        imageUrl=imageUrl,
        type= type.name,
        amount=amount,
        year = date.year,
        month = date.monthValue,
        dayOfMonth = date.dayOfMonth,
        calories=calories,
        id=id
    )
}