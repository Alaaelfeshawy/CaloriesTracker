package com.example.tracker_data.repository

import com.example.tracker_data.local.dao.TrackerDao
import com.example.tracker_data.mapper.toProductModel
import com.example.tracker_data.mapper.toTrackedFoodEntity
import com.example.tracker_data.mapper.toTrackedFoodModel
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_domain.model.ProductModel
import com.example.tracker_domain.model.TrackedFoodModel
import com.example.tracker_domain.repository.ITrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepositoryImpl @Inject constructor(
    private val dao: TrackerDao,
    private val api: OpenFoodApi,
) : ITrackerRepository {
    override suspend fun search(query: String, page: Int, pageSize: Int): Result<List<ProductModel>> {
        return try {
            val search = api.search(query, page, pageSize)
            Result.success(search.products.mapNotNull { it.toProductModel() })
        }catch (e:Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertFood(trackedFood: TrackedFoodModel) {
        dao.insert(trackedFood.toTrackedFoodEntity())
    }

    override suspend fun deleteFood(trackedFood: TrackedFoodModel) {
        dao.delete(trackedFood.toTrackedFoodEntity())
    }

    override fun getFoodByDate(date: LocalDate): Flow<List<TrackedFoodModel>> {
       return dao.getFoodByDate(
           day = date.dayOfMonth ,
           month = date.monthValue ,
           year = date.year
       ).map {
           entities -> entities.map {
               it.toTrackedFoodModel()
       }
       }
    }
}