package com.example.prepmate

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val fabHome = findViewById<FloatingActionButton>(R.id.fabHome)
        val tvPageTitle = findViewById<TextView>(R.id.tvPageTitle)

        // 1. جلب الـ NavController (المتحكم في التنقل)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // 2. ربط الشريط السفلي بالـ NavController أوتوماتيكياً
        bottomNavigationView.setupWithNavController(navController)
        // تعطيل الضغط على العنصر الوهمي اللي في النص
        bottomNavigationView.menu.findItem(R.id.nav_placeholder).isEnabled = false

        // 3. برمجة زرار الـ Home العائم عشان يفتح الـ HomeFragment
        fabHome.setOnClickListener {
            navController.navigate(R.id.nav_home)

            // إزالة التحديد من القائمة السفلية عشان الزرار الأخضر بس اللي يبان
            bottomNavigationView.menu.setGroupCheckable(0, true, false)
            for (i in 0 until bottomNavigationView.menu.size()) {
                bottomNavigationView.menu.getItem(i).isChecked = false
            }
            bottomNavigationView.menu.setGroupCheckable(0, true, true)
        }

        // 4. تغيير عنوان الصفحة (Top Bar) تلقائياً لما الشاشة تتغير
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.nav_home -> tvPageTitle.text = "Home"
                R.id.nav_search -> tvPageTitle.text = "Search"
                R.id.nav_favorites -> tvPageTitle.text = "Favorites"
                R.id.nav_plan -> tvPageTitle.text = "Plan"
                R.id.nav_profile -> tvPageTitle.text = "Profile"
            }
        }

        // 5. إزالة التحديد الافتراضي عند فتح التطبيق لأول مرة
        bottomNavigationView.menu.setGroupCheckable(0, true, false)
        for (i in 0 until bottomNavigationView.menu.size()) {
            bottomNavigationView.menu.getItem(i).isChecked = false
        }
        bottomNavigationView.menu.setGroupCheckable(0, true, true)
    }
}