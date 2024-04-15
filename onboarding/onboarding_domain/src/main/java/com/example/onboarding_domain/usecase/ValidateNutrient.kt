package com.example.onboarding_domain.usecase

import com.example.core.R
import com.example.core.util.UiText

class ValidateNutrient {

    fun invoke(
         carbRatio : String ,
         proteinRatio : String ,
         fatsRatio : String,
    ) : Result{
        val carb = carbRatio.toIntOrNull()
        val protein = proteinRatio.toIntOrNull()
        val fats = fatsRatio.toIntOrNull()
        if (carb == null || protein == null || fats == null){
            return Result.Error(message = UiText.StaticMessage(R.string.error_invalid_values))
        }
        if (carb + protein + fats != 100){
            return Result.Error(message = UiText.StaticMessage(R.string.error_not_100_percent))
        }

        return Result.Success(
            carbRatio = carb.div(100f),
            proteinRatio = protein.div(100f),
            fatsRatio = fats.div(100f),
        )
    }
}

sealed class Result{
    data class Success(
        val carbRatio : Float,
        val proteinRatio : Float ,
        val fatsRatio : Float ,
    ) : Result()
    data class Error(val message :UiText) : Result()
}