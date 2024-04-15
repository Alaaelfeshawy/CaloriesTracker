package com.example.onboarding_presentation.height

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
class HeightViewModel @Inject constructor(
   private val preferences: Preferences,
   private val filterOutDigits: FilterOutDigits,
) : ViewModel(){

    var height = mutableStateOf("")

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun saveHeight(height:String){
        if (height.length <= 3){
            this.height.value = filterOutDigits.filterOutDigit(height)
        }else{
          viewModelScope.launch {
              _uiEvent.send(
                  UiEvent.ShowSnackBar(UiText.StaticMessage(R.string.error_height_cant_be_empty))
              )
          }
        }
    }
    fun onNextClick(){
        viewModelScope.launch {
            val heightNumber = height.value.toIntOrNull() ?: run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StaticMessage(R.string.error_age_cant_be_empty))
                )
                return@launch
            }
            preferences.saveAge(heightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }
}