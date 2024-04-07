package com.example.core.domain.model

data class UserInfo(
    var age : Int,
    var gender: Gender ,
    var activityLevel: ActivityLevel ,
    var goalType: GoalType,
    var weight : Float,
    var height : Float,
    var proteinRatio : Float,
    var carbRatio : Float,
    var fatRatio : Float,
)
