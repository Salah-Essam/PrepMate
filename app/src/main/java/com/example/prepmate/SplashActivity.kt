package com.example.prepmate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val auth = FirebaseAuth.getInstance()
            // جلب حالة الزائر المحفوظة
            val prefs = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            val isGuest = prefs.getBoolean("isGuest", false)

            // لو مسجل دخول أو داخل كزائر، روح للرئيسية مباشرة
            val intent = if (auth.currentUser != null || isGuest) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 3000)
    }
}