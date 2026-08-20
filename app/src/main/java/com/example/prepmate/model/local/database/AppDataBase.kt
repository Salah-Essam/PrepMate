package com.example.prepmate.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prepmate.model.local.dao.FavouriteMealDAO
import com.example.prepmate.model.local.entity.FavoriteMealEntity
import kotlin.jvm.java

@Database(entities = [FavoriteMealEntity::class] , version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract val favoriteMealDao : FavouriteMealDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            // If INSTANCE is not null, return it.
            // If it is null, create the database.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "prepmate_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}