package com.example.anew

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button
    lateinit var signBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize the views
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)
        signBtn = findViewById(R.id.signup_redirect)

        // Login button click listener
        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            Log.i("Test Credentials", "Username: $username and Password: $password")

            // Check if credentials are valid (for demo, we check if they are not empty)
            if (username.isNotEmpty() && password.isNotEmpty()) {
                // If valid, start MainActivity2
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                finish()  // Close the current activity (MainActivity)
            } else {
                // Handle invalid credentials, e.g., show an error message
                Log.i("Login", "Invalid credentials")
            }
        }

        // Signup redirect button click listener (can add functionality to navigate to signup page)
        signBtn.setOnClickListener {
            // You can implement this later if needed
            Log.i("Signup", "Redirecting to Signup Page")

        val intent = Intent(this, MainActivity4::class.java)
        startActivity(intent)

        }
    }
}
