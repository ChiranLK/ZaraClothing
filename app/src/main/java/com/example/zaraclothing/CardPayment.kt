package com.example.zaraclothing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class CardPayment : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get references to EditTexts for credit card number, expiry date, and security code
        val cardNumberEditText: EditText = findViewById(R.id.CardNumber)
        val expiryDateEditText: EditText = findViewById(R.id.CardYear)
        val securityCodeEditText: EditText = findViewById(R.id.CardSecurity)
        val paymentButton: Button = findViewById(R.id.paymentbutton)

        // Payment button click listener
        paymentButton.setOnClickListener {
            if (validatePaymentDetails(cardNumberEditText, expiryDateEditText, securityCodeEditText)) {
                val intent = Intent(this, ShippingInformation::class.java)
                startActivity(intent) // Proceed to the next screen if validation passes
            }
        }

        val previousImg: ImageView = findViewById(R.id.previousImg)
        previousImg.setOnClickListener {
            val intent = Intent(this, paymentMethodSC1::class.java)
            startActivity(intent) // Navigate back to the previous screen
        }

        // Navigation bar setup
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

    // Validation function
    private fun validatePaymentDetails(
        cardNumber: EditText,
        expiryDate: EditText,
        securityCode: EditText
    ): Boolean {
        var isValid = true

        val cardNumberText = cardNumber.text.toString().trim()
        val expiryDateText = expiryDate.text.toString().trim()
        val securityCodeText = securityCode.text.toString().trim()

        // Credit Card Number Validation: Cannot be empty
        if (TextUtils.isEmpty(cardNumberText)) {
            cardNumber.error = "Credit card number cannot be empty"
            isValid = false
        }

        // Expiry Date Validation: Must be in YYYY/MM/DD format
        if (TextUtils.isEmpty(expiryDateText)) {
            expiryDate.error = "Expiry date cannot be empty"
            isValid = false
        } else if (!expiryDateText.matches(Regex("\\d{4}/\\d{2}/\\d{2}"))) {
            expiryDate.error = "Invalid expiry date format. Use YYYY/MM/DD"
            isValid = false
        }

        // Security Code Validation: Cannot be empty
        if (TextUtils.isEmpty(securityCodeText)) {
            securityCode.error = "Security code cannot be empty"
            isValid = false
        }

        return isValid
    }
}
