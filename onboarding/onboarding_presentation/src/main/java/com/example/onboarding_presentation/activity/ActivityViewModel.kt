package com.example.onboarding_presentation.activity

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.prefrences.Preferences
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val preferences : Preferences
) : ViewModel(){

    var selectedActivity = mutableStateOf<ActivityLevel>(ActivityLevel.Low)

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun saveActivityLevel(activityLevel :ActivityLevel){
        selectedActivity.value = activityLevel
    }

    fun onNextClick(){
        viewModelScope.launch {
            preferences.saveActivityLevel(selectedActivity.value)
            _uiEvent.send(UiEvent.Navigate(Route.GOAL))
        }
    }
}