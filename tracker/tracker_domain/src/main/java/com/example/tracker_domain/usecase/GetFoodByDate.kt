package com.example.tracker_domain.usecase

import com.example.tracker_domain.model.TrackedFoodModel
import com.example.tracker_domain.repository.ITrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetFoodByDate @Inject constructor(
    private val repository: ITrackerRepository
){

    fun invoke(date : LocalDate) : Flow<List<TrackedFoodModel>> {
        return repository.getFoodByDate(date)
    }
}