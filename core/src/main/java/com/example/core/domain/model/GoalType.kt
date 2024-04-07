package com.example.core.domain.model

sealed class GoalType(val goalType : String) {

    data object KeepWeight : GoalType("keep_wight")
    data object GainWeight : GoalType("gain_weight")
    data object LoseWeight : GoalType("lose_weight")

    companion object {
        fun fromString (goalType : String) : GoalType {
            return when(goalType){
                "keep_wight"-> KeepWeight
                "gain_weight"-> GainWeight
                "lose_weight"-> LoseWeight
                else -> KeepWeight
            }
        }
    }
}