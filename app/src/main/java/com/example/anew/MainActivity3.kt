package com.example.anew

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {

    lateinit var fullnameInput: EditText
    lateinit var idInput: EditText
    lateinit var amountInput: EditText
    lateinit var donateBtn: Button

    @SuppressLint("CutPasteId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        fullnameInput = findViewById(R.id.donation_name)
        idInput = findViewById(R.id.id_card_number)
        amountInput = findViewById(R.id.donation_amount)
        donateBtn = findViewById(R.id.btn_donation)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        donateBtn.setOnClickListener {
            val fullName = fullnameInput.text.toString().trim()
            val idNumber = idInput.text.toString().trim()
            val amount = amountInput.text.toString().trim()

            // Input validation
            if (fullName.isEmpty()) {
                fullnameInput.error = "Full name is required"
                return@setOnClickListener
            }

            if (idNumber.isEmpty()) {
                idInput.error = "ID number is required"
                return@setOnClickListener
            }

            if (amount.isEmpty()) {
                amountInput.error = "Amount is required"
                return@setOnClickListener
            }

            // If all fields are filled, navigate to next screen
            val intent = Intent(this@MainActivity3, MainActivity7::class.java)
            startActivity(intent)
            finish()
        }

        // Navigation buttons
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
