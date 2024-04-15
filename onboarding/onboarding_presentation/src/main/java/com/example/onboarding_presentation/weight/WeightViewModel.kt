package com.example.onboarding_presentation.weight

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.domain.prefrences.Preferences
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {

    var weight = mutableStateOf("")

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun saveWeight(weight:String){
        if (weight.length <= 3){
            this.weight.value = weight
        }else{
            viewModelScope.launch {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StaticMessage(R.string.error_weight_cant_be_empty))
                )
            }
        }
    }
    fun onNextClick(){
        viewModelScope.launch {
            val weightNumber = weight.value.toFloatOrNull() ?: run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StaticMessage(R.string.error_weight_cant_be_empty))
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.ACTIVITY))
        }
    }
}