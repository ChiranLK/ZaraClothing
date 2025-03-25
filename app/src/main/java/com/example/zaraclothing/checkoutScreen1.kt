package com.example.zaraclothing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class checkoutScreen1 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_screen1)

        val quantityPicker: NumberPicker = findViewById(R.id.quantityPicker)
        val addToCart: Button = findViewById(R.id.Addtocart)

        quantityPicker.minValue = 1
        quantityPicker.maxValue = 10
        quantityPicker.value = 1

        addToCart.setOnClickListener {
            val selectedQuantity = quantityPicker.value
            val unitPrice = 35 // Updated price from XML
            val totalPrice = selectedQuantity * unitPrice

            val intent = Intent(this, Cart::class.java)
            intent.putExtra("quantity", selectedQuantity)
            intent.putExtra("totalPrice", totalPrice)
            startActivity(intent)
        }

        val previousImg: ImageView = findViewById(R.id.previousImg)
        previousImg.setOnClickListener {
            val intent = Intent(this, TshirtSection::class.java)
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
