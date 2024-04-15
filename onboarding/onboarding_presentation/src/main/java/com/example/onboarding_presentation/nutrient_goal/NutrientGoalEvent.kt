package com.example.onboarding_presentation.nutrient_goal

sealed class NutrientGoalEvent {
    data class OnCarb(val carbRatio : String):NutrientGoalEvent()
    data class OnProtein(val proteinRatio : String):NutrientGoalEvent()
    data class OnFat(val fatRatio : String):NutrientGoalEvent()
    data object OnNext:NutrientGoalEvent()
}