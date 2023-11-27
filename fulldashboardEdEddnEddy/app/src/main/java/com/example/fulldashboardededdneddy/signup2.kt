package com.example.fulldashboardededdneddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fulldashboardededdneddy.databinding.ActivitySignup2Binding
import com.google.firebase.auth.FirebaseAuth

class signup2 : AppCompatActivity() {

    private lateinit var binding:ActivitySignup2Binding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

            binding.register.setOnClickListener {
                val email = binding.editTextText3.text.toString()
                val pass = binding.editTextTextPassword.text.toString()
                val confirmPass = binding.confirmpass2.text.toString()

                if (email.isNotBlank() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                    if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, Loginpage::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this , it.exception.toString() , Toast.LENGTH_SHORT).show()
                        }
                    }
                    } else{
                        Toast.makeText(this , "Password is not matching" , Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this , "Empty Fields Are Not Allowed!" , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
