package com.example.zaraclothing

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.NumberPicker


class checkoutScreen1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout_screen1)
        // Size Selection
        val sizeRadioGroup: RadioGroup = findViewById(R.id.sizeRadioGroup)
        sizeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedSize = when (checkedId) {
                R.id.sizeSmall -> "Small"
                R.id.sizeMedium -> "Medium"
                R.id.sizeLarge -> "Large"
                R.id.sizeXL -> "XL"
                else -> ""
            }

            val quantityPicker: NumberPicker = findViewById(R.id.quantityPicker)

            // Set min and max values programmatically
            quantityPicker.minValue = 1
            quantityPicker.maxValue = 10

            // Optional: Set default value
            quantityPicker.value = 1
        }
    }
}
