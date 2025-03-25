package com.example.zaraclothing

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginSC1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_sc1)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginUsername: EditText = findViewById(R.id.loginSCUserName)
        val loginPassword: EditText = findViewById(R.id.loginSCPassword)
        val loginSignup: Button = findViewById(R.id.loginSCsignup)
        val loginSignin: Button = findViewById(R.id.loginSCsignin)

        // Navigate to Register Screen
        loginSignup.setOnClickListener {
            val intent = Intent(this, RegisterScreen1::class.java)
            startActivity(intent)
        }

        // Sign In Button Click
        loginSignin.setOnClickListener {
            if (validateInputs(loginUsername, loginPassword)) {
                val intent = Intent(this, homeScreen1::class.java)
                startActivity(intent)
            }
        }
    }

    // Validation Function
    private fun validateInputs(username: EditText, password: EditText): Boolean {
        var isValid = true

        val usernameText = username.text.toString().trim()
        val passwordText = password.text.toString().trim()

        // Username Validation (Cannot be empty and must contain @)
        if (usernameText.isEmpty()) {
            username.error = "Username cannot be empty"
            isValid = false
        } else if (!usernameText.contains("@")) {
            username.error = "Username must contain @"
            isValid = false
        }

        // Password Validation (Cannot be empty and must be at least 6 characters)
        if (passwordText.isEmpty()) {
            password.error = "Password cannot be empty"
            isValid = false
        } else if (passwordText.length < 6) {
            password.error = "Password must be at least 6 characters"
            isValid = false
        }

        return isValid
    }
}
