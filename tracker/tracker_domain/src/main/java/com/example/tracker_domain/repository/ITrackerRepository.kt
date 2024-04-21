package com.example.tracker_domain.repository

import com.example.tracker_domain.model.ProductModel
import com.example.tracker_domain.model.TrackedFoodModel
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface ITrackerRepository {
    suspend fun search(
        query : String,
        page : Int,
        pageSize:Int
    ): Result<List<ProductModel>>

    suspend fun insertFood(trackedFood: TrackedFoodModel)
    suspend fun deleteFood(trackedFood:TrackedFoodModel)

    fun getFoodByDate(date : LocalDate) : Flow<List<TrackedFoodModel>>

}