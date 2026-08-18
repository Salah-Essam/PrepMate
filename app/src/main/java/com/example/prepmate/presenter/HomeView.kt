package com.example.prepmate.presenter

import com.example.prepmate.model.Area
import com.example.prepmate.model.Category
import com.example.prepmate.model.Meal

interface HomeView {

    fun showRandomMeal(meal: Meal)

    fun showCategories(categories: List<Category>)

    fun showAreas(areas: List<Area>)

    fun showError(message: String)
}