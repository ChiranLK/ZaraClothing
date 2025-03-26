package com.example.zaraclothing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Cart : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Retrieve the quantity and total price from intent
        val quantity = intent.getIntExtra("quantity", 1)
        val totalPrice = intent.getIntExtra("totalPrice", 35) // Updated default price

        // Update UI
        val totalPriceTextView: TextView = findViewById(R.id.totalPriceTextView)
        val quantityTextView: TextView = findViewById(R.id.quantityTextView)

        quantityTextView.text = "Quantity: $quantity"
        totalPriceTextView.text = "Total: LKR$totalPrice"

        // Proceed to payment button
        val proceedToPay: Button = findViewById(R.id.proceedtopay)
        proceedToPay.setOnClickListener {
            val intent = Intent(this, paymentMethodSC1::class.java)
            startActivity(intent)
        }

        // Back button
        val previousImg: ImageView = findViewById(R.id.previousImg)
        previousImg.setOnClickListener {
            val intent = Intent(this, checkoutScreen1::class.java)
            startActivity(intent)
        }

        // Bottom Navigation Setup
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
