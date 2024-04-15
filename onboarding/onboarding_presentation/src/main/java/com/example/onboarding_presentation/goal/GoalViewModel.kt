package com.example.onboarding_presentation.goal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.GoalType
import com.example.core.domain.prefrences.Preferences
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences : Preferences
) : ViewModel(){

    var selectedGoal = mutableStateOf<GoalType>(GoalType.LoseWeight)

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun saveGoalType(goalType :GoalType){
        selectedGoal.value = goalType
    }

    fun onNextClick(){
        viewModelScope.launch {
            preferences.saveGoalType(selectedGoal.value)
            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
        }
    }
}