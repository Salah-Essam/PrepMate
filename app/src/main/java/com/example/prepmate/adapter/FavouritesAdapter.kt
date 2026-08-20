package com.example.prepmate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prepmate.R
import com.example.prepmate.model.Meal
import com.example.prepmate.model.local.entity.FavoriteMealEntity
import io.reactivex.rxjava3.core.Flowable

class FavouritesAdapter(private var mealsList : List<FavoriteMealEntity>)
    : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val mealImage : ImageView = view.findViewById(R.id.image_meal)
        val mealTitle : TextView = view.findViewById(R.id.text_meal_name)
        val mealCategory : TextView = view.findViewById(R.id.text_category)
        val mealArea: TextView = view.findViewById(R.id.text_area)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_card , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentMeal = mealsList[position]
        holder.mealTitle.text = currentMeal.name
        holder.mealCategory.text = currentMeal.category
        holder.mealArea.text = currentMeal.originArea

        Glide.with(holder.itemView.context)
            .load(currentMeal.imageUrl)
            .into(holder.mealImage)
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }





}