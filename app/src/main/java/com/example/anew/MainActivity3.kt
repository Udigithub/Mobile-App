package com.example.anew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.anew.MainActivity4

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val donateButton = findViewById<Button>(R.id.btn_donate)

        // Set onClickListener for sign-up button
        donateButton.setOnClickListener {
            // Logic for handling the sign-up process
            // Here you would add any validation and saving user data
            // For now, we'll simulate a successful registration and go to MainActivity (login screen)

            // Navigate to MainActivity (login screen)
            val intent = Intent(this@MainActivity3, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}