package com.example.prepmate.presenter

import com.example.prepmate.adapter.SearchItem
import com.example.prepmate.repository.SearchRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchPresenter(
    private val view: SearchView,
    private val repository: SearchRepository
) {
    private val disposables = CompositeDisposable()

    fun loadCategories() {
        val disposable = repository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val items = response.categories.map {
                    SearchItem(it.strCategory, it.strCategoryThumb ?: "")
                }
                view.showSearchResults(items)
            }, { error -> view.showError(error.message ?: "Error loading categories") })
        disposables.add(disposable)
    }

    fun loadIngredients() {
        val disposable = repository.getIngredients()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                // هناخد أول 50 مكون بس عشان ميبقاش تقيل على الشاشة
                val items = response.meals.take(50).map {
                    // رابط أيقونات المكونات الرسمي من TheMealDB
                    SearchItem(it.strIngredient, "https://www.themealdb.com/images/ingredients/${it.strIngredient}.png")
                }
                view.showSearchResults(items)
            }, { error -> view.showError(error.message ?: "Error loading ingredients") })
        disposables.add(disposable)
    }

    fun loadCountries() {
        val disposable = repository.getAreas()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val items = response.meals.map {
                    SearchItem(it.strArea, getFlagUrl(it.strArea))
                }
                // فلترة أي دولة مجهولة مش معروفة
                view.showSearchResults(items.filter { it.imageUrl.isNotEmpty() })
            }, { error -> view.showError(error.message ?: "Error loading countries") })
        disposables.add(disposable)
    }

    // دالة مساعدة لربط اسم الدولة برمز العلم بتاعها
    private fun getFlagUrl(area: String): String {
        val countryCode = when (area) {
            "American" -> "us"; "British" -> "gb"; "Canadian" -> "ca"; "Chinese" -> "cn"
            "Croatian" -> "hr"; "Dutch" -> "nl"; "Egyptian" -> "eg"; "Filipino" -> "ph"
            "French" -> "fr"; "Greek" -> "gr"; "Indian" -> "in"; "Irish" -> "ie"
            "Italian" -> "it"; "Jamaican" -> "jm"; "Japanese" -> "jp"; "Kenyan" -> "ke"
            "Malaysian" -> "my"; "Mexican" -> "mx"; "Moroccan" -> "ma"; "Polish" -> "pl"
            "Portuguese" -> "pt"; "Russian" -> "ru"; "Spanish" -> "es"; "Thai" -> "th"
            "Tunisian" -> "tn"; "Turkish" -> "tr"; "Vietnamese" -> "vn"
            else -> ""
        }
        return if (countryCode.isNotEmpty()) "https://www.themealdb.com/images/icons/flags/big/64/$countryCode.png" else ""
    }

    fun clear() {
        disposables.clear()
    }
}