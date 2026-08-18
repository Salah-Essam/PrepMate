package com.example.prepmate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prepmate.adapter.AreaAdapter
import com.example.prepmate.adapter.CategoryAdapter
import com.example.prepmate.model.Area
import com.example.prepmate.model.Category
import com.example.prepmate.model.Meal
import com.example.prepmate.presenter.HomePresenter
import com.example.prepmate.presenter.HomeView
import com.example.prepmate.repository.HomeRepository

class HomeFragment : Fragment(), HomeView {

    private lateinit var presenter: HomePresenter

    private lateinit var mealImage: ImageView
    private lateinit var mealName: TextView
    private lateinit var mealCategory: TextView
    private lateinit var mealArea: TextView

    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var areasAdapter: AreaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        initializeRecyclerViews(view)

        presenter = HomePresenter(
            this,
            HomeRepository()
        )

        presenter.loadHomeData()
    }

    private fun initializeViews(view: View) {

        mealImage = view.findViewById(R.id.ivMealImage)
        mealName = view.findViewById(R.id.tvMealName)
        mealCategory = view.findViewById(R.id.tvMealCategory)
        mealArea = view.findViewById(R.id.tvMealArea)
    }

    private fun initializeRecyclerViews(view: View) {

        val categoriesRecyclerView =
            view.findViewById<RecyclerView>(R.id.rvCategories)

        val areasRecyclerView =
            view.findViewById<RecyclerView>(R.id.rvAreas)

        categoriesAdapter = CategoryAdapter()
        areasAdapter = AreaAdapter()

        categoriesRecyclerView.apply {

            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

            adapter = categoriesAdapter

            isNestedScrollingEnabled = false
        }

        areasRecyclerView.apply {

            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

            adapter = areasAdapter

            isNestedScrollingEnabled = false
        }
    }

    override fun showRandomMeal(meal: Meal) {

        mealName.text = meal.strMeal

        mealCategory.text =
            "Category: ${meal.strCategory ?: "Unknown"}"

        mealArea.text =
            "Country: ${meal.strArea ?: "Unknown"}"

        Glide.with(this)
            .load(meal.strMealThumb)
            .into(mealImage)
    }

    override fun showCategories(
        categories: List<Category>
    ) {

        categoriesAdapter.updateCategories(categories)
    }

    override fun showAreas(
        areas: List<Area>
    ) {

        areasAdapter.updateAreas(areas)
    }

    override fun showError(message: String) {

        // We will replace this with a proper UI error message later.
        // For now, it prevents the application from crashing.
    }

    override fun onDestroyView() {

        presenter.clear()

        super.onDestroyView()
    }
}