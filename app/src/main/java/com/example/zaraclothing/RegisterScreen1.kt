package com.example.zaraclothing

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterScreen1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_screen1)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val firstName: EditText = findViewById(R.id.registerSCfname)
        val lastName: EditText = findViewById(R.id.registerSClname)
        val email: EditText = findViewById(R.id.registerSCemail)
        val password: EditText = findViewById(R.id.registerSCpassword)
        val registerSCsubmit: Button = findViewById(R.id.registerSCsubmit)

        // Real-time validation
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateInputs(firstName, lastName, email, password)
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        firstName.addTextChangedListener(textWatcher)
        lastName.addTextChangedListener(textWatcher)
        email.addTextChangedListener(textWatcher)
        password.addTextChangedListener(textWatcher)

        registerSCsubmit.setOnClickListener {
            if (validateInputs(firstName, lastName, email, password)) {
                val intent = Intent(this, homeScreen1::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validateInputs(
        firstName: EditText,
        lastName: EditText,
        email: EditText,
        password: EditText
    ): Boolean {
        var isValid = true

        if (firstName.text.toString().trim().isEmpty()) {
            firstName.error = "First Name is required"
            isValid = false
        }

        if (lastName.text.toString().trim().isEmpty()) {
            lastName.error = "Last Name is required"
            isValid = false
        }

        if (email.text.toString().trim().isEmpty()) {
            email.error = "Email is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Enter a valid email (must contain @)"
            isValid = false
        }

        if (password.text.toString().trim().isEmpty()) {
            password.error = "Password is required"
            isValid = false
        } else if (password.text.toString().length < 6) {
            password.error = "Password must be at least 6 characters"
            isValid = false
        }

        return isValid
    }
}