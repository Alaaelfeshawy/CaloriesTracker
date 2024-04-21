package com.example.core.domain

import android.content.SharedPreferences
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.prefrences.Preferences
import javax.inject.Inject

class DefaultPreferences @Inject constructor(
    private val sharedPref : SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPref.edit()
            .putString(Preferences.KEY_GENDER , gender.gender)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit()
            .putInt(Preferences.KEY_AGE , age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_WEIGHT , weight)
            .apply()
    }

    override fun saveHeight(height: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_HEIGHT , height)
            .apply()
    }

    override fun saveActivityLevel(activityLevel: ActivityLevel) {
        sharedPref.edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL , activityLevel.activity)
            .apply()
    }

    override fun saveGoalType(goalType: GoalType) {
        sharedPref.edit()
            .putString(Preferences.KEY_GOAL_TYPE , goalType.goalType)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_PROTEIN_RATIO , ratio)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_CARB_RATIO , ratio)
            .apply()    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Preferences.KEY_FAT_RATIO , ratio)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPref.getInt(Preferences.KEY_AGE , -1)
        val weight = sharedPref.getFloat(Preferences.KEY_WEIGHT , -1f)
        val height = sharedPref.getFloat(Preferences.KEY_HEIGHT , -1f)
        val gender = sharedPref.getString(Preferences.KEY_GENDER , "") ?: "male"
        val proteinRatio = sharedPref.getFloat(Preferences.KEY_PROTEIN_RATIO , -1f)
        val fatRatio = sharedPref.getFloat(Preferences.KEY_FAT_RATIO , -1f)
        val carbRatio = sharedPref.getFloat(Preferences.KEY_CARB_RATIO , -1f)
        val activityLevel = sharedPref.getString(Preferences.KEY_ACTIVITY_LEVEL , "") ?: ""
        val goalType = sharedPref.getString(Preferences.KEY_GOAL_TYPE , "") ?: ""
        return UserInfo(
            age = age,
            gender= Gender.fromString(gender) ,
            activityLevel= ActivityLevel.fromString(activityLevel),
            goalType= GoalType.fromString(goalType),
            weight = weight,
            height = height,
            proteinRatio = proteinRatio ,
            carbRatio = carbRatio ,
            fatRatio = fatRatio,
        )
    }

    override fun saveShouldLoadOnboarding(value: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_SHOULD_LOAD_ONBOARDING , value)
            .apply()
    }

    override fun getShouldLoadOnboarding(): Boolean {
        return sharedPref.getBoolean(Preferences.KEY_SHOULD_LOAD_ONBOARDING , true)
    }
}