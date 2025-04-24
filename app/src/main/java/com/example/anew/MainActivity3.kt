package com.example.anew

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity3 : AppCompatActivity() {

    lateinit var fullnameInput: EditText
    lateinit var idInput: EditText
    lateinit var amountInput: EditText
    lateinit var donateBtn: Button

    private lateinit var userDatabase: DatabaseReference

    @SuppressLint("CutPasteId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        // Firebase reference
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        userDatabase = database.reference.child("donations")

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

            val donation = Donation(fullName, idNumber, amount)
            Log.d("DonationDebug", "Attempting to write: $donation")

            userDatabase.push().setValue(donation)
                .addOnSuccessListener {
                    Log.d("DonationDebug", "Data write success")
                    Toast.makeText(this, "Donation submitted successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MainActivity3, MainActivity7::class.java))
                    finish()
                }
                .addOnFailureListener {
                    Log.e("DonationDebug", "Data write failed", it)
                    Toast.makeText(this, "Failed to submit donation: ${it.message}", Toast.LENGTH_LONG).show()
                }
        }


        // Navigation buttons
        findViewById<ImageButton>(R.id.btn_home).setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        findViewById<ImageButton>(R.id.btn_search).setOnClickListener {
            startActivity(Intent(this, MainActivity6::class.java))
        }

        findViewById<ImageButton>(R.id.btn_donate).setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }

        findViewById<ImageButton>(R.id.btn_profile).setOnClickListener {
            startActivity(Intent(this, MainActivity5::class.java))
        }
    }

    // Data class outside the method for better practice
    data class Donation(
        val fullName: String,
        val idNumber: String,
        val amount: String
    )
}
