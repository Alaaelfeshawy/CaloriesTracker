package com.example.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Gender
import com.example.core.domain.prefrences.Preferences
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences : Preferences
) : ViewModel(){

    var selectedGender = mutableStateOf<Gender>(Gender.Male)

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun saveGender(gender :Gender){
       selectedGender.value = gender
    }

    fun onNextClick(){
        viewModelScope.launch {
            preferences.saveGender(selectedGender.value)
            _uiEvent.send(UiEvent.Navigate(Route.AGE))
        }
    }
}