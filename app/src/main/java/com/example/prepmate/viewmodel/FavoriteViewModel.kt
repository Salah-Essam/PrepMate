package com.example.prepmate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.prepmate.model.local.entity.FavoriteMealEntity
import com.example.prepmate.repository.FavoriteRepository

class FavoriteViewModel(val repository: FavoriteRepository) : ViewModel() {

    private val _mealsList : LiveData<List<FavoriteMealEntity>> =
        repository.getAllFavoriteMeal().toLiveData()

    val mealList : LiveData<List<FavoriteMealEntity>> get() = _mealsList


}