package com.example.tracker_data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trackedFoodEntity: TrackedFoodEntity)
    @Delete
    suspend fun delete(trackedFoodEntity: TrackedFoodEntity)

    @Query("Select * from trackedfoodentity where dayOfMonth = :day and month=:month and year=:year ")
    fun getFoodByDate(day : Int , month :Int , year :Int) : Flow<List<TrackedFoodEntity>>

}