package com.ambiongltb.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ambiongltb.fulldashboardededdneddy.databinding.ActivityLoginpageBinding
import com.google.firebase.auth.FirebaseAuth

class Loginpage : AppCompatActivity() {


    private lateinit var binding: ActivityLoginpageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginpageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        binding.signupbutton.setOnClickListener {
            val intent = Intent(this, signup2::class.java)
            startActivity(intent)
        }

        binding.loginbutton.setOnClickListener {
            val email = binding.editTextText5.text.toString()
            val pass = binding.editTextText6.text.toString()

            if (email.isNotBlank() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, com.ambiongltb.fulldashboardededdneddy.MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are Not Allowed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
