package com.example.zaraclothing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TshirtSection : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tshirt_section)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val OversizeT: ImageView = findViewById(R.id.OversizeT)

        OversizeT.setOnClickListener {
            val intent = Intent(this, checkoutScreen1::class.java)
            startActivity(intent)
        }
        val previousImg: ImageView = findViewById(R.id.previousImg)

        previousImg.setOnClickListener {
            val intent = Intent(this, MensSection::class.java)
            startActivity(intent)
        }
    }
}