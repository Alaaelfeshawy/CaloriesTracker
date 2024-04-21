package com.example.tracker_domain.usecase

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.ProductModel
import com.example.tracker_domain.model.TrackedFoodModel
import com.example.tracker_domain.repository.ITrackerRepository
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

class InsertFood @Inject constructor(
    private val repository: ITrackerRepository
) {
    suspend fun invoke(
        model: ProductModel,
        amount : Int,
        mealType: MealType,
        date: LocalDate)  {
        repository.insertFood(
            TrackedFoodModel(
                name = model.name,
                date = date,
                type = mealType,
                protein = ((model.proteins100g / 100f) * amount).roundToInt(),
                calories =  ((model.energyKcal100g / 100f) * amount).roundToInt(),
                carbs =  ((model.carbohydrates100g / 100f) * amount).roundToInt(),
                fat =  ((model.fat100g / 100f) * amount).roundToInt(),
                imageUrl = model.image,
                amount = amount
            )
        )
    }
}