package com.example.prepmate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prepmate.R
import com.google.android.material.imageview.ShapeableImageView

// كلاس بسيط لاستقبال البيانات بأشكالها المختلفة
data class SearchItem(val name: String, val imageUrl: String)

class SearchItemAdapter(
    private var items: List<SearchItem> = emptyList()
) : RecyclerView.Adapter<SearchItemAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ShapeableImageView = itemView.findViewById(R.id.ivSearchItemImage)
        val itemName: TextView = itemView.findViewById(R.id.tvSearchItemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_card, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name

        // تحميل الصورة باستخدام Glide
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<SearchItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}