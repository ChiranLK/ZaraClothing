package com.example.zaraclothing

import SliderAdapter
import android.accounts.Account
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class homeScreen1 : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private val imageList = listOf(
        R.drawable.homescreenlogo,
        R.drawable.banner0,
        R.drawable.banner1,
        R.drawable.banner2

    )


    private val handler = Handler(Looper.getMainLooper())
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            val nextItem = (viewPager.currentItem + 1) % imageList.size
            viewPager.setCurrentItem(nextItem, true)
            handler.postDelayed(this, 3000) // Auto-slide every 3 seconds
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen1) // ✅ Use the correct XML file

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = SliderAdapter(imageList)

        startAutoSlide()

        val HomeMenbutton: TextView = findViewById(R.id.HomeMenbutton)

        HomeMenbutton.setOnClickListener()
        {
            val intent = Intent(this, MensSection::class.java)
            startActivity(intent)
        }
        val navibar : BottomNavigationView = findViewById(R.id.navibar)

        navibar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.npoints -> {
                    startActivity(Intent(this, LoyaltyPoints::class.java))
                    true
                }
                R.id.nuser -> {
                    startActivity(Intent(this,UserProfile::class.java))
                    true
                }
                R.id.nCart -> {
                    startActivity(Intent(this,Cart::class.java))
                    true
                }
                R.id.nhome->true
                else -> false
            }
        }
    }

    private fun startAutoSlide() {
        handler.postDelayed(autoScrollRunnable, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(autoScrollRunnable)
    }

}
