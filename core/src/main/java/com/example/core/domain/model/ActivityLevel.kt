package com.example.core.domain.model

sealed class ActivityLevel(val activity : String) {

    data object Low : ActivityLevel("low_activity")
    data object Medium : ActivityLevel("medium_activity")
    data object High : ActivityLevel("high_activity")

    companion object {
        fun fromString (activity : String) : ActivityLevel {
            return when(activity){
                "low_activity"-> Low
                "medium_activity"-> Medium
                "high_activity"-> High
                else -> Medium
            }
        }
    }
}