package com.example.tracker_domain.usecase

import com.example.tracker_domain.model.TrackedFoodModel
import com.example.tracker_domain.repository.ITrackerRepository
import javax.inject.Inject

class DeleteFood  @Inject constructor(
    private val repository: ITrackerRepository
) {
    suspend fun invoke(trackedFood: TrackedFoodModel)  {
         repository.deleteFood(trackedFood)
    }
}