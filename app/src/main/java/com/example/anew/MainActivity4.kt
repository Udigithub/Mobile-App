package com.example.anew


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity4 : AppCompatActivity() {

    lateinit var fullnameInput: EditText
    lateinit var emailInput: EditText
    lateinit var uNameInput: EditText
    lateinit var pwordInput: EditText
    lateinit var confirmPword: EditText
    lateinit var signUpBtn: Button
    lateinit var loginRediBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val userDatabase: DatabaseReference = database.reference.child("users")


        // Initialize the views
        fullnameInput = findViewById(R.id.fullname_input)
        emailInput = findViewById(R.id.email_input)
        uNameInput = findViewById(R.id.username_input)
        pwordInput = findViewById(R.id.password_input)
        confirmPword = findViewById(R.id.confirm_password_input)
        signUpBtn = findViewById(R.id.signup_btn)
        loginRediBtn = findViewById(R.id.login_redirect)

        // Edge-to-edge window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        signUpBtn.setOnClickListener {

            data class User(
                val fullname: String,
                val email: String,
                val username: String,
                val password: String
            )

            val fullname = fullnameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val username = uNameInput.text.toString().trim()
            val password = pwordInput.text.toString().trim()
            val confirmPassword = confirmPword.text.toString().trim()

            if (fullname.isNotEmpty() && email.isNotEmpty() &&
                username.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
            ) {
                if (password == confirmPassword) {
                    // Create User object
                    val user = User(fullname, email, username, password)

                    // Save data to Firebase Realtime Database
                    userDatabase.push().setValue(user).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()
                            // Redirect to Login Activity (MainActivity)
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Failed to register. Please try again.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    Log.i("SignUp", "User registered: $fullname, $email")
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        loginRediBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
