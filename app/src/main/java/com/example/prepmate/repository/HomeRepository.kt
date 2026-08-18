package com.example.prepmate.repository

import com.example.prepmate.model.AreasResponse
import com.example.prepmate.model.CategoriesResponse
import com.example.prepmate.model.MealResponse
import com.example.prepmate.network.RetrofitClient
import io.reactivex.rxjava3.core.Single

class HomeRepository {

    fun getRandomMeal(): Single<MealResponse> {
        return RetrofitClient.apiService.getRandomMeal()
    }

    fun getCategories(): Single<CategoriesResponse> {
        return RetrofitClient.apiService.getCategories()
    }

    fun getAreas(): Single<AreasResponse> {
        return RetrofitClient.apiService.getAreas()
    }
}