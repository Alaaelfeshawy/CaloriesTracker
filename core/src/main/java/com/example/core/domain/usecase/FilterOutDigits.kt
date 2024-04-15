package com.example.core.domain.usecase

class FilterOutDigits {
     fun filterOutDigit(text:String):String {
        return text.filter { it.isDigit() }
    }
}