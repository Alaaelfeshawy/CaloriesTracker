package com.example.tracker_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.prefrences.Preferences
import com.example.tracker_domain.usecase.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
): ViewModel() {
    var state by mutableStateOf(TrackerOverviewState())
        private set
    private var job:Job?=null
    init {
        preferences.saveShouldLoadOnboarding(false)
    }

    fun onEvent(event: TrackerOverviewEvent){
        when(event){
            is TrackerOverviewEvent.OnAddFoodClick -> TODO()
            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
               viewModelScope.launch {
                   trackerUseCases.deleteFood.invoke(event.trackedFood)
                   refreshFood()
               }
            }
            TrackerOverviewEvent.OnNextDayClick -> {
                state = state.copy(
                    date = state.date.plusDays(1)
                )
            }
            TrackerOverviewEvent.OnPreviousDayClick -> {
                state = state.copy(
                    date = state.date.minusDays(1)
                )
            }
            is TrackerOverviewEvent.OnToggleMealClick -> {
                 state =  state.copy(
                     meals = state.meals.map {
                         if (it.name == event.meal.name){
                             it.copy(isExpanded = true)
                         }else it
                     }
                 )
            }
        }
    }

    private fun refreshFood(){
        job?.cancel()
        job = trackerUseCases.getFoodByDate.invoke(
            state.date
        ).onEach {
            foods->
            val nutrientsResult = trackerUseCases.calculateNutrients.invoke(foods)
            state = state.copy(
                totalCarbs = nutrientsResult.totalCarb,
                totalProtein = nutrientsResult.totalProtein,
                totalFat = nutrientsResult.totalFats,
                totalCalories = nutrientsResult.totalCalories,
                carbsGoal = nutrientsResult.carbGoal,
                proteinGoal = nutrientsResult.proteinGoal,
                fatGoal = nutrientsResult.fatGoal,
                caloriesGoal = nutrientsResult.caloryGoal,
                trackedFoods = foods,
                meals = state.meals.map {
                    val nutrientsForMeal = nutrientsResult.nutrientMeal[it.mealType]
                            ?: return@map it.copy(
                                carbs = 0,
                                protein = 0,
                                fat = 0,
                                calories = 0
                            )
                    it.copy(
                        carbs = nutrientsForMeal.carb,
                        protein = nutrientsForMeal.protein,
                        fat = nutrientsForMeal.fat,
                        calories = nutrientsForMeal.calory
                    )
                }
            )
        }.launchIn(viewModelScope)
    }
}