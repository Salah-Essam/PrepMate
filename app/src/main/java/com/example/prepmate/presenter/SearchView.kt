package com.example.prepmate.presenter

import com.example.prepmate.adapter.SearchItem

interface SearchView {
    fun showSearchResults(items: List<SearchItem>)
    fun showError(message: String)
}