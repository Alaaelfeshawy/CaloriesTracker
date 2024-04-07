package com.example.caloriestracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.core.domain.DefaultPreferences
import com.example.core.domain.prefrences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app :Application) : SharedPreferences{
        return app.getSharedPreferences("shared_pref",MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPref :SharedPreferences) : Preferences{
        return DefaultPreferences(sharedPref)
    }
}