package com.example.zaraclothing

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterScreen1 : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_screen1)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val firstName: EditText = findViewById(R.id.registerfname)
        val lastName: EditText = findViewById(R.id.registerlname)
        val email: EditText = findViewById(R.id.registeremail)
        val password: EditText = findViewById(R.id.registerpassword)
        val checkBox: CheckBox = findViewById(R.id.registercheckBox)
        val registerSCsubmit: Button = findViewById(R.id.registersubmit)

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
            if (validateInputs(firstName, lastName, email, password) && checkBox.isChecked) {
                val editor = sharedPreferences.edit()
                editor.putString("firstName", firstName.text.toString().trim())
                editor.putString("lastName", lastName.text.toString().trim())
                editor.putString("email", email.text.toString().trim())
                editor.putString("password", password.text.toString().trim())
                editor.apply()

                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, homeScreen1::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields correctly and agree to terms", Toast.LENGTH_SHORT).show()
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
