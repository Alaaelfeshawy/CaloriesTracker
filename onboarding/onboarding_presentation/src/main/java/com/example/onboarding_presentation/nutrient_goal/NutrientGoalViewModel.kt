package com.example.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.prefrences.Preferences
import com.example.core.domain.usecase.FilterOutDigits
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.onboarding_domain.usecase.Result
import com.example.onboarding_domain.usecase.ValidateNutrient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
   private val preferences: Preferences,
   private val filterOutDigits: FilterOutDigits,
   private val validateNutrient : ValidateNutrient
) : ViewModel(){

  var state = mutableStateOf(NutrientGoalState())

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent){
        when(event){
            is NutrientGoalEvent.OnCarb ->{
               state.value =  state.value.copy(carbRatio = event.carbRatio)
            }
            is NutrientGoalEvent.OnProtein ->{
                state.value =  state.value.copy(proteinRatio = event.proteinRatio)
            }
            is NutrientGoalEvent.OnFat ->{
                state.value =  state.value.copy(fatsRatio = event.fatRatio)
            }
            is NutrientGoalEvent.OnNext ->{
                val result = validateNutrient.invoke(
                    carbRatio = state.value.carbRatio,
                    proteinRatio =  state.value.proteinRatio,
                    fatsRatio =  state.value.fatsRatio,
                    )
                when(result){
                    is Result.Success ->{
                        viewModelScope.launch {
                            preferences.saveCarbRatio(result.carbRatio)
                            preferences.saveProteinRatio(result.proteinRatio)
                            preferences.saveFatRatio(result.fatsRatio)
                            _uiEvent.send(
                                UiEvent.Navigate(Route.TRACKER_OVERVIEW)
                            )
                        }
                    }
                    is Result.Error ->{
                        viewModelScope.launch {
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(result.message)
                            )
                        }
                    }
                }
            }
        }
    }

}