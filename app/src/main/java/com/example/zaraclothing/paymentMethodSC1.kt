package com.example.zaraclothing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class paymentMethodSC1 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_method_sc1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val Cardpayment: ImageView = findViewById(R.id.Cardpayment)

        Cardpayment.setOnClickListener {
            val intent = Intent(this, CardPayment::class.java)
            startActivity(intent)// need to change
        }
        val previousImg: ImageView = findViewById(R.id.previousImg)

        previousImg.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        val navibar: BottomNavigationView = findViewById(R.id.navibar)
        navibar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.npoints -> {
                    startActivity(Intent(this, LoyaltyPoints::class.java))
                    true
                }
                R.id.nuser -> {
                    startActivity(Intent(this, UserProfile::class.java))
                    true
                }
                R.id.nCart -> {
                    startActivity(Intent(this, Cart::class.java))
                    true
                }
                R.id.nhome -> {
                    startActivity(Intent(this, homeScreen1::class.java))
                    true
                }
                else -> false
            }
        }
    }
}