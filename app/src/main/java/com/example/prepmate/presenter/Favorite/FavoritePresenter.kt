package com.example.prepmate.presenter.Favorite

import com.example.prepmate.model.local.entity.FavoriteMealEntity
import com.example.prepmate.repository.FavoriteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class FavoritePresenter(
    val favoriteRepository: FavoriteRepository  ,
    val view : FavoriteContract.View
    )
    : FavoriteContract.Presenter {

    override fun getFavoriteMeals() {
        favoriteRepository.getAllFavoriteMeal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                normallist -> view.showFavoriteMeals(normallist)
            }
    }

}