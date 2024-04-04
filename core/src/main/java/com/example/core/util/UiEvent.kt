package com.example.core.util

sealed class UiEvent {
    data class Navigate (var route : String) : UiEvent()
    object NavigateUp  : UiEvent()
}