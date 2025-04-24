package com.example.anew

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.anew.R.id.btn_donate
import com.google.firebase.database.*
import android.widget.TextView


class MainActivity5 : AppCompatActivity() {

    private lateinit var fullnameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var donationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        fullnameTextView = findViewById(R.id.fullname_label)
        emailTextView = findViewById(R.id.email_label)
        donationTextView = findViewById(R.id.donation_details)

        // Get data from Firebase
        val dbRefUsers = FirebaseDatabase.getInstance().reference.child("users")
        val dbRefDonations = FirebaseDatabase.getInstance().reference.child("donations")

        // Fetch latest user (for demo purposes – in real app you'd use a user ID or auth)
        dbRefUsers.limitToLast(1).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnap in snapshot.children) {
                    val fullName = userSnap.child("fullname").getValue(String::class.java)
                    val email = userSnap.child("email").getValue(String::class.java)

                    fullnameTextView.text = "Name: ${fullName ?: "N/A"}"
                    emailTextView.text = "Email: ${email ?: "N/A"}"
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        // Fetch latest donation
        dbRefDonations.limitToLast(1).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (donationSnap in snapshot.children) {
                    val amount = donationSnap.child("amount").getValue(String::class.java)
                    donationTextView.text = "Donation: ${amount ?: "N/A"}"
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

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
}
