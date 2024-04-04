package com.example.caloriestracker.navigation

import androidx.navigation.NavController
import com.example.core.util.UiEvent

fun NavController.navigateTo(uiEvent: UiEvent.Navigate){
    this.navigate(uiEvent.route)
}