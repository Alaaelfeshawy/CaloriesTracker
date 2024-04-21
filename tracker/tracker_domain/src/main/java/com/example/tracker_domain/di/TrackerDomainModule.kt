package com.example.tracker_domain.di

import com.example.core.domain.prefrences.Preferences
import com.example.tracker_domain.repository.ITrackerRepository
import com.example.tracker_domain.usecase.CalculateNutrients
import com.example.tracker_domain.usecase.DeleteFood
import com.example.tracker_domain.usecase.GetFoodByDate
import com.example.tracker_domain.usecase.InsertFood
import com.example.tracker_domain.usecase.SearchFood
import com.example.tracker_domain.usecase.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @Provides
    @ViewModelScoped
    fun provideTrackerDomainUseCases(preferences: Preferences , repository: ITrackerRepository):TrackerUseCases{
        return TrackerUseCases(
            calculateNutrients = CalculateNutrients(preferences),
            searchFood = SearchFood(repository),
            deleteFood = DeleteFood(repository),
            insertFood = InsertFood(repository),
            getFoodByDate = GetFoodByDate(repository)
        )
    }
}