package com.example.anew

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity4 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge layout
        setContentView(R.layout.activity_main4) // Set the layout for this activity

        // Set listener for applying window insets for edge-to-edge compatibility
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find sign-up button
        val signUpButton = findViewById<Button>(R.id.signup_btn)

        // Set onClickListener for sign-up button
        signUpButton.setOnClickListener {
            // Logic for handling the sign-up process
            // Here you would add any validation and saving user data
            // For now, we'll simulate a successful registration and go to MainActivity (login screen)

            // Navigate to MainActivity (login screen)
            val intent = Intent(this@MainActivity4, MainActivity::class.java)
            startActivity(intent)
            finish() // Close this activity (Create Account page) after navigating

        }
        val sigUpButton = findViewById<Button>(R.id.login_redirect)

        // Set onClickListener for sign-up button
        sigUpButton.setOnClickListener {
            // Logic for handling the sign-up process
            // Here you would add any validation and saving user data
            // For now, we'll simulate a successful registration and go to MainActivity (login screen)

            // Navigate to MainActivity (login screen)
            val intent = Intent(this@MainActivity4, MainActivity::class.java)
            startActivity(intent)
            finish()

            // You can add more logic here if needed (e.g., handling form data, error messages, etc.)
        }
    }
}
