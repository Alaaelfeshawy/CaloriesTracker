package com.example.onboarding_presentation.age

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.domain.prefrences.Preferences
import com.example.core.domain.usecase.FilterOutDigits
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
   private val preferences: Preferences,
   private val filterOutDigits: FilterOutDigits,
) : ViewModel(){

    var age = mutableStateOf("")

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun saveAge(age:String){
        if (age.length <= 3){
            this.age.value = filterOutDigits.filterOutDigit(age)
        }else{
          viewModelScope.launch {
              _uiEvent.send(
                  UiEvent.ShowSnackBar(UiText.StaticMessage(R.string.error_age_cant_be_empty))
              )
          }
        }
    }
    fun onNextClick(){
        viewModelScope.launch {
            val ageNumber = age.value.toIntOrNull() ?: run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StaticMessage(R.string.error_age_cant_be_empty))
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }
}