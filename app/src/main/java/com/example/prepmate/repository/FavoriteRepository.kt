package com.example.prepmate.repository

import com.example.prepmate.model.Meal
import com.example.prepmate.model.local.dao.FavouriteMealDAO
import com.example.prepmate.model.local.entity.FavoriteMealEntity
import com.example.prepmate.model.mapper.toFavoriteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class FavoriteRepository(val favouriteMealDAO: FavouriteMealDAO) {

    fun addFavoriteMeal(meal : Meal) : Completable{
        val favouriteMeal = meal.toFavoriteEntity()
        return favouriteMealDAO.addFavoriteMeal(favouriteMeal)
    }

    fun removeFavoriteMeal(meal : Meal) : Completable{
        val favouriteMeal = meal.toFavoriteEntity()
        return favouriteMealDAO.removeFavoriteMeal(favouriteMeal)
    }

    fun getAllFavoriteMeal() : Flowable<List<FavoriteMealEntity>>{
        val favoriteMealsList = favouriteMealDAO.ListFavoriteMeals()
        return favoriteMealsList
    }
}