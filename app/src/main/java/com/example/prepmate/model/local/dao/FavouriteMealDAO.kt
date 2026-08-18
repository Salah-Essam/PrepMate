package com.example.prepmate.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.prepmate.model.Meal
import com.example.prepmate.model.local.entity.FavoriteMealEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface FavouriteMealDAO {
    @Insert
    fun addFavoriteMeal(favoriteMealEntity: FavoriteMealEntity ) : Completable

    @Delete
    fun removeFavoriteMeal(favoriteMealEntity: FavoriteMealEntity) : Completable

    @Query("SELECT * FROM favorite_meals")
    fun ListFavoriteMeals() : Flowable<List<FavoriteMealEntity>>

}