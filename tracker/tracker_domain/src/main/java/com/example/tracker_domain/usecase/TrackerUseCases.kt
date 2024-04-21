package com.example.tracker_domain.usecase

data class TrackerUseCases(
    var calculateNutrients: CalculateNutrients,
    var deleteFood: DeleteFood,
    var getFoodByDate: GetFoodByDate,
    var insertFood: InsertFood,
    var searchFood: SearchFood,
)
