package com.example.prepmate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prepmate.R
import com.example.prepmate.model.Area

class AreaAdapter(
    private var areas: List<Area> = emptyList()
) : RecyclerView.Adapter<AreaAdapter.AreaViewHolder>() {

    class AreaViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val areaName: TextView =
            itemView.findViewById(R.id.tvAreaName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AreaViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_area, parent, false)

        return AreaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AreaViewHolder,
        position: Int
    ) {
        holder.areaName.text =
            areas[position].strArea
    }

    override fun getItemCount(): Int {
        return areas.size
    }

    fun updateAreas(newAreas: List<Area>) {
        areas = newAreas
        notifyDataSetChanged()
    }
}