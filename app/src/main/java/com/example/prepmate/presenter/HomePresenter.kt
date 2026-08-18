package com.example.prepmate.presenter

import com.example.prepmate.repository.HomeRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomePresenter(
    private val view: HomeView,
    private val repository: HomeRepository
) {

    private val disposables = CompositeDisposable()

    fun loadHomeData() {

        loadRandomMeal()
        loadCategories()
        loadAreas()
    }

    private fun loadRandomMeal() {

        val disposable = repository
            .getRandomMeal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    val meal = response.meals.firstOrNull()

                    if (meal != null) {
                        view.showRandomMeal(meal)
                    } else {
                        view.showError("No meal found")
                    }
                },
                { error ->

                    view.showError(
                        "Failed to load meal: ${error.message}"
                    )
                }
            )

        disposables.add(disposable)
    }

    private fun loadCategories() {

        val disposable = repository
            .getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    view.showCategories(response.categories)
                },
                { error ->

                    view.showError(
                        "Failed to load categories: ${error.message}"
                    )
                }
            )

        disposables.add(disposable)
    }

    private fun loadAreas() {

        val disposable = repository
            .getAreas()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    view.showAreas(response.meals)
                },
                { error ->

                    view.showError(
                        "Failed to load countries: ${error.message}"
                    )
                }
            )

        disposables.add(disposable)
    }

    fun clear() {
        disposables.clear()
    }
}