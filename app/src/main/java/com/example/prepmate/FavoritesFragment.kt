package com.example.prepmate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prepmate.adapter.FavouritesAdapter
import com.example.prepmate.model.local.entity.FavoriteMealEntity
import com.example.prepmate.presenter.Favorite.FavoriteContract
import com.example.prepmate.presenter.Favorite.FavoritePresenter
import org.jetbrains.annotations.Contract


class FavoritesFragment : Fragment() , FavoriteContract.View{

    lateinit var presenter:FavoritePresenter
    lateinit var adapter: FavouritesAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.favorites_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavouritesAdapter(emptyList())

        presenter.getFavoriteMeals()
    }

    override fun showFavoriteMeals(meals: List<FavoriteMealEntity>) {
        adapter.updateData(meals)
    }


}

