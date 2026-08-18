package com.example.prepmate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prepmate.network.RetrofitClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testApiConnection()
    }

    private fun testApiConnection() {

        RetrofitClient.apiService
            .getRandomMeal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    val meal = response.meals.firstOrNull()

                    Log.d(
                        "PrepMateAPI",
                        "SUCCESS: ${meal?.strMeal}"
                    )

                    Log.d(
                        "PrepMateAPI",
                        "Category: ${meal?.strCategory}"
                    )

                    Log.d(
                        "PrepMateAPI",
                        "Area: ${meal?.strArea}"
                    )
                },
                { error ->

                    Log.e(
                        "PrepMateAPI",
                        "ERROR: ${error.message}",
                        error
                    )
                }
            )
    }
}