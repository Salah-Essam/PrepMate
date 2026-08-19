package com.example.prepmate.model.mapper

import com.example.prepmate.model.Meal
import com.example.prepmate.model.local.entity.FavoriteMealEntity

fun Meal.toFavoriteEntity(): FavoriteMealEntity {
    return FavoriteMealEntity(
        // Clean names mapped to API names
        id = this.idMeal,
        name = this.strMeal,
        category = this.strCategory ?: "",
        originArea = this.strArea ?: "",
        instructions = this.strInstructions ?: "",
        imageUrl = this.strMealThumb ?: "",
        youtubeUrl = this.strYoutube ?: "",

        // Ingredients
        strIngredient1 = this.strIngredient1,
        strIngredient2 = this.strIngredient2,
        strIngredient3 = this.strIngredient3,
        strIngredient4 = this.strIngredient4,
        strIngredient5 = this.strIngredient5,
        strIngredient6 = this.strIngredient6,
        strIngredient7 = this.strIngredient7,
        strIngredient8 = this.strIngredient8,
        strIngredient9 = this.strIngredient9,
        strIngredient10 = this.strIngredient10,
        strIngredient11 = this.strIngredient11,
        strIngredient12 = this.strIngredient12,
        strIngredient13 = this.strIngredient13,
        strIngredient14 = this.strIngredient14,
        strIngredient15 = this.strIngredient15,
        strIngredient16 = this.strIngredient16,
        strIngredient17 = this.strIngredient17,
        strIngredient18 = this.strIngredient18,
        strIngredient19 = this.strIngredient19,
        strIngredient20 = this.strIngredient20,

        // Measures
        strMeasure1 = this.strMeasure1,
        strMeasure2 = this.strMeasure2,
        strMeasure3 = this.strMeasure3,
        strMeasure4 = this.strMeasure4,
        strMeasure5 = this.strMeasure5,
        strMeasure6 = this.strMeasure6,
        strMeasure7 = this.strMeasure7,
        strMeasure8 = this.strMeasure8,
        strMeasure9 = this.strMeasure9,
        strMeasure10 = this.strMeasure10,
        strMeasure11 = this.strMeasure11,
        strMeasure12 = this.strMeasure12,
        strMeasure13 = this.strMeasure13,
        strMeasure14 = this.strMeasure14,
        strMeasure15 = this.strMeasure15,
        strMeasure16 = this.strMeasure16,
        strMeasure17 = this.strMeasure17,
        strMeasure18 = this.strMeasure18,
        strMeasure19 = this.strMeasure19,
        strMeasure20 = this.strMeasure20
    )
}