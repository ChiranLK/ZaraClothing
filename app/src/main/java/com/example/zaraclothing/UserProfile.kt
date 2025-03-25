package com.example.zaraclothing

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserProfile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
        val userhistory: ImageView = findViewById(R.id.userhistory)

        userhistory.setOnClickListener {
            val intent = Intent(this, Orderhistory::class.java)
            startActivity(intent)
        }

        val usercart: ImageView = findViewById(R.id.usercart)

        usercart.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        lateinit var sharedPreferences: SharedPreferences

            sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

            val firstName = sharedPreferences.getString("firstName", "Not Available")
            val lastName = sharedPreferences.getString("lastName", "Not Available")
            val email = sharedPreferences.getString("email", "Not Available")
            val password = sharedPreferences.getString("password", "Not Available")

            val userNameText: TextView = findViewById(R.id.profileFirstName)
            val userLastText: TextView = findViewById(R.id.profileLastName)
            val userEmailText: TextView = findViewById(R.id.profileEmail)
            val userPasswordText: TextView = findViewById(R.id.profilePassword)
            val logoutButton: Button = findViewById(R.id.logoutButton)

            userNameText.text = firstName
            userLastText.text = lastName
            userEmailText.text = email
            userPasswordText.text = password

            logoutButton.setOnClickListener {
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                val intent = Intent(this, LoginSC1::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

