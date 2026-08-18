package com.example.prepmate.model

data class IngredientsResponse(
    val meals: List<Ingredient>
)

data class Ingredient(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String?
)