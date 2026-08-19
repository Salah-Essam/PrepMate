package com.example.prepmate.repository

import com.example.prepmate.model.AreasResponse
import com.example.prepmate.model.CategoriesResponse
import com.example.prepmate.model.IngredientsResponse
import com.example.prepmate.network.RetrofitClient
import io.reactivex.rxjava3.core.Single

class SearchRepository {
    fun getCategories(): Single<CategoriesResponse> = RetrofitClient.apiService.getCategories()
    fun getAreas(): Single<AreasResponse> = RetrofitClient.apiService.getAreas()
    fun getIngredients(): Single<IngredientsResponse> = RetrofitClient.apiService.getIngredients()
}