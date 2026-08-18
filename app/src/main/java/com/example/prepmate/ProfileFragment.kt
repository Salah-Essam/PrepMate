package com.example.prepmate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (FirebaseAuth.getInstance().currentUser == null) {
            return createGuestView()
        }

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (FirebaseAuth.getInstance().currentUser == null) return

        val tvProfileEmail = view.findViewById<TextView>(R.id.tvProfileEmail)
        val btnGoToFavorites = view.findViewById<MaterialButton>(R.id.btnGoToFavorites)
        val btnGoToPlan = view.findViewById<MaterialButton>(R.id.btnGoToPlan)
        val btnLogout = view.findViewById<MaterialButton>(R.id.btnLogout)

        val currentUser = FirebaseAuth.getInstance().currentUser
        tvProfileEmail.text = currentUser?.email ?: "Unknown User"

        // 2. برمجة زرار الانتقال للمفضلة باستخدام NavController
        btnGoToFavorites.setOnClickListener {
            // تنبيه: تأكد إن ID المفضلة في ملف nav_graph.xml هو favoritesFragment
            findNavController().navigate(R.id.nav_favorites)
        }

        // 3. برمجة زرار الانتقال لخطة الوجبات باستخدام NavController
        btnGoToPlan.setOnClickListener {
            // تنبيه: تأكد إن ID الخطة في ملف nav_graph.xml هو planFragment
            findNavController().navigate(R.id.nav_plan)
        }

        // 4. برمجة زرار تسجيل الخروج
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            GoogleSignIn.getClient(requireContext(), gso).signOut()

            requireActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                .edit().putBoolean("isGuest", false).apply()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

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
            text = "You need to log in to view your Profile."
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