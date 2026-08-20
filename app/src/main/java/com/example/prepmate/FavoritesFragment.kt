package com.example.prepmate

import FavoriteViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prepmate.adapter.FavouritesAdapter
import com.example.prepmate.model.local.database.AppDataBase
import com.example.prepmate.repository.FavoriteRepository
import com.example.prepmate.viewmodel.FavoriteViewModel
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class FavoritesFragment : Fragment() {

    // تهيئة الـ ViewModel بناءً على الكود القادم من Remote
    val viewModel : FavoriteViewModel by viewModels {
        val database = AppDataBase.getInstance(requireContext())
        val dao = database.favoriteMealDao
        val repository = FavoriteRepository(dao)
        FavoriteViewModelFactory(repository)
    }

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

        // الحماية من الكراش لو المستخدم زائر (من نسختك)
        if (FirebaseAuth.getInstance().currentUser == null) return

        recyclerView = view.findViewById<RecyclerView>(R.id.favorites_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // تهيئة الأแดبتر بقائمة فارغة وربطه بالـ RecyclerView أولاً (من نسختك)
        adapter = FavouritesAdapter(emptyList())
        recyclerView.adapter = adapter

        // مراقبة البيانات القادمة من الـ ViewModel وتحديث الأแดبتر (النظام الجديد)
        viewModel.mealList.observe(viewLifecycleOwner) { mealList ->
            adapter.updateData(mealList)
        }
    }

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
}