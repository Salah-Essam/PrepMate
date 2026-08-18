package com.example.prepmate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prepmate.adapter.SearchItem
import com.example.prepmate.adapter.SearchItemAdapter
import com.example.prepmate.presenter.SearchPresenter
import com.example.prepmate.presenter.SearchView
import com.example.prepmate.repository.SearchRepository
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText

class SearchFragment : Fragment(), SearchView {

    private lateinit var searchAdapter: SearchItemAdapter
    private lateinit var presenter: SearchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etSearch = view.findViewById<TextInputEditText>(R.id.etSearch)
        val chipGroupFilters = view.findViewById<ChipGroup>(R.id.chipGroupFilters)
        val rvSearchResults = view.findViewById<RecyclerView>(R.id.rvSearchResults)

        // إعداد الـ Adapter والـ Presenter
        searchAdapter = SearchItemAdapter()
        rvSearchResults.layoutManager = GridLayoutManager(requireContext(), 2)
        rvSearchResults.adapter = searchAdapter

        presenter = SearchPresenter(this, SearchRepository())

        // برمجة زراير الفلتر لتغيير الـ Hint وتحميل البيانات الحقيقية
        chipGroupFilters.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                when (checkedIds.first()) {
                    R.id.chipCategory -> {
                        etSearch.hint = "Search by Category"
                        presenter.loadCategories() // تحميل التصنيفات
                    }
                    R.id.chipIngredient -> {
                        etSearch.hint = "Search by Ingredient"
                        presenter.loadIngredients() // تحميل المكونات
                    }
                    R.id.chipCountry -> {
                        etSearch.hint = "Search by Country"
                        presenter.loadCountries() // تحميل الدول
                    }
                }
            }
        }

        // تحميل التصنيفات كوضع افتراضي أول ما الشاشة تفتح
        presenter.loadCategories()
    }

    override fun showSearchResults(items: List<SearchItem>) {
        searchAdapter.updateItems(items)
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        presenter.clear()
        super.onDestroyView()
    }
}