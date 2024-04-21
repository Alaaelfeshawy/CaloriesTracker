package com.example.tracker_domain.usecase

import com.example.tracker_domain.model.ProductModel
import com.example.tracker_domain.repository.ITrackerRepository
import javax.inject.Inject

class SearchFood @Inject constructor(
    private val repository: ITrackerRepository
) {

    suspend fun invoke(query : String, page : Int, pageSize:Int) : Result<List<ProductModel>> {
        if (query.isBlank()){
            return Result.success(emptyList())
        }
        return repository.search(query, page, pageSize)
    }
}