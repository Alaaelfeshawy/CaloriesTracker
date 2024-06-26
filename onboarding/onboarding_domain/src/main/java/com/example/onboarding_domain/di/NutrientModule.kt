package com.example.onboarding_domain.di

import com.example.onboarding_domain.usecase.ValidateNutrient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object NutrientModule {

    @Provides
    @ViewModelScoped
    fun provideNutrientUseCase(): ValidateNutrient {
        return ValidateNutrient()
    }
}