package com.example.core.util

import android.content.Context

sealed class UiText {

    data class DynamicMessage( val message : String) : UiText()
    data class StaticMessage( val resId : Int) : UiText()

    fun asString(context:Context):String{
        return when(this){
            is DynamicMessage -> message
            is StaticMessage-> context.getString(resId)
        }
    }
}