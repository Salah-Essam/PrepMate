package com.example.prepmate

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
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
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class FavoritesFragment : Fragment() , FavoriteContract.View{

    lateinit var presenter:FavoritePresenter
    lateinit var adapter: FavouritesAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // التحقق مما إذا كان المستخدم زائر
        if (FirebaseAuth.getInstance().currentUser == null) {
            return createGuestView()
        }

        // في حالة كان مسجل دخول، اعرض الواجهة الطبيعية
        return inflater.inflate(R.layout.fragment_favorites, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.favorites_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavouritesAdapter(emptyList())

        presenter.getFavoriteMeals()
    // دالة لبناء واجهة الزائر برمجياً
    private fun createGuestView(): View {
        val context = requireContext()
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        val textView = TextView(context).apply {
            text = "You need to log in to view your Favorites."
            textSize = 18f
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }

        val button = MaterialButton(context).apply {
            text = "Login Now"
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.topMargin = 16
            layoutParams = params

            setOnClickListener {
                val intent = Intent(context, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        layout.addView(textView)
        layout.addView(button)

        return layout
    }

    override fun showFavoriteMeals(meals: List<FavoriteMealEntity>) {
        adapter.updateData(meals)
    }


}

