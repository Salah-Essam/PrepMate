package com.example.prepmate.presenter.Favorite

import com.example.prepmate.model.local.entity.FavoriteMealEntity
import io.reactivex.rxjava3.core.Flowable

interface FavoriteContract {

    interface View{
        fun showFavoriteMeals(meals : List<FavoriteMealEntity>)
    }
    interface Presenter{
        fun getFavoriteMeals()
    }
}