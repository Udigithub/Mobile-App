package com.example.anew

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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