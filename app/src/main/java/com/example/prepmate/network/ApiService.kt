package com.example.prepmate.network

import com.example.prepmate.model.AreasResponse
import com.example.prepmate.model.CategoriesResponse
import com.example.prepmate.model.IngredientsResponse
import com.example.prepmate.model.MealResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("random.php")
    fun getRandomMeal(): Single<MealResponse>

    @GET("categories.php")
    fun getCategories(): Single<CategoriesResponse>

    @GET("list.php?a=list")
    fun getAreas(): Single<AreasResponse>

    @GET("list.php?i=list")
    fun getIngredients(): Single<IngredientsResponse>
}