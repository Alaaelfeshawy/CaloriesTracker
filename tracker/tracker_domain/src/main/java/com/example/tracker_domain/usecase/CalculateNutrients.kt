package com.example.tracker_domain.usecase

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.prefrences.Preferences
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFoodModel
import javax.inject.Inject
import kotlin.math.roundToInt

class CalculateNutrients @Inject constructor(
    private val preferences : Preferences
) {
    fun invoke(trackedFoods : List<TrackedFoodModel>):Result{
        val allNutrientsMeal = trackedFoods
            .groupBy { it.type }
            .mapValues {
                val type =it.key
                val food = it.value
                MealNutrient(
                    protein = food.sumOf { it.protein },
                    carb = food.sumOf { it.carbs },
                    fat = food.sumOf { it.fat },
                    calory = food.sumOf { it.calories },
                    mealType = type
                )
            }

        val totalCarb = allNutrientsMeal.values.sumOf { it.carb }
        val totalProtein = allNutrientsMeal.values.sumOf { it.protein }
        val totalFat = allNutrientsMeal.values.sumOf { it.fat }
        val totalCalories = allNutrientsMeal.values.sumOf { it.calory }

        val userInfo = preferences.loadUserInfo()
        val caloryGoal = dailyCaloryRequirement(userInfo)
        val carbsGoal = (caloryGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloryGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloryGoal = caloryGoal,
            totalCarb = totalCarb,
            totalProtein = totalProtein,
            totalFats = totalFat,
            totalCalories = totalCalories,
            nutrientMeal= allNutrientsMeal
        )

    }

    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female ->  {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }

    data class MealNutrient(
        var protein : Int,
        var carb : Int,
        var fat : Int,
        var calory : Int,
        var mealType : MealType
    )
    data class Result(
        var carbGoal : Int,
        var proteinGoal : Int,
        var fatGoal : Int,
        var caloryGoal : Int,
        var totalCarb : Int,
        var totalProtein : Int,
        var totalFats : Int,
        var totalCalories : Int,
        var nutrientMeal : Map<MealType , MealNutrient>
    )
}