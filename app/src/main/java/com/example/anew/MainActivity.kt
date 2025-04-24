package com.example.anew

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*



class MainActivity : AppCompatActivity() {

    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button
    lateinit var signBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val userDatabase: DatabaseReference = database.reference.child("users")


        // Initialize the views
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)
        signBtn = findViewById(R.id.signup_redirect)

        // Login button click listener
        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Check in Firebase Database
                userDatabase.orderByChild("username").equalTo(username)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                var validUser = false
                                for (userSnap in snapshot.children) {
                                    val dbPassword = userSnap.child("password").value.toString()
                                    if (dbPassword == password) {
                                        validUser = true
                                        break
                                    }
                                }

                                if (validUser) {
                                    Toast.makeText(this@MainActivity, "Login successful", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@MainActivity, "Incorrect password", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(this@MainActivity, "Username not found", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@MainActivity, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
            } else {
                Toast.makeText(this, "Please fill the username and password correctly", Toast.LENGTH_SHORT).show()
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
