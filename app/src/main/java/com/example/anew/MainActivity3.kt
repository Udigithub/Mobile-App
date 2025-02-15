package com.example.anew

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.anew.MainActivity4
import com.example.anew.R.id.btn_donate

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("CutPasteId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val donateButton = findViewById<Button>(R.id.btn_donation)

        // Set onClickListener for sign-up button
        donateButton.setOnClickListener {
            // Logic for handling the sign-up process
            // Here you would add any validation and saving user data
            // For now, we'll simulate a successful registration and go to MainActivity (login screen)

            // Navigate to MainActivity (login screen)
            val intent = Intent(this@MainActivity3, MainActivity7::class.java)
            startActivity(intent)
            finish()

        }
        val imageButton1 = findViewById<ImageButton>(R.id.btn_home)
        imageButton1.setOnClickListener {
            val intentToLogin = Intent(this, MainActivity2::class.java)
            startActivity(intentToLogin)
        }

        val imageButton2 = findViewById<ImageButton>(R.id.btn_search)
        imageButton2.setOnClickListener {
            val intentToLogin = Intent(this, MainActivity6::class.java)
            startActivity(intentToLogin)
        }
        val imageButton3 = findViewById<ImageButton>(R.id.btn_donate)
        imageButton3.setOnClickListener {
            val intentToLogin = Intent(this, MainActivity3::class.java)
            startActivity(intentToLogin)
        }

        val imageButton4 = findViewById<ImageButton>(R.id.btn_profile)
        imageButton4.setOnClickListener {
            val intentToLogin = Intent(this, MainActivity5::class.java)
            startActivity(intentToLogin)
        }
    }
}