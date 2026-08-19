package com.example.prepmate.model.local.database

import androidx.room.Database
import com.example.prepmate.model.local.dao.FavouriteMealDAO
import com.example.prepmate.model.local.entity.FavoriteMealEntity

@Database(entities = [FavoriteMealEntity::class] , version = 1)
abstract class AppDataBase {
    abstract val favoriteMealDao : FavouriteMealDAO
}