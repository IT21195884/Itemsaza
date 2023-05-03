package com.ayush.quizapp.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.itemsaza.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.net.Inet4Address

class SignUpActivity : AppCompatActivity() {
    lateinit var etFullname: EditText
    lateinit var etEmail: EditText
    lateinit var etAddress: EditText
    lateinit var etMobile: EditText
    lateinit var etNIC: EditText
    private lateinit var etPass: EditText
    lateinit var etConfPass: EditText
    private lateinit var btnTerms: Button       //Terms and Conditions page
    private lateinit var btnSignUp: Button
    lateinit var tvRedirectLogin: TextView

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)

        // View Bindings
        etFullname = findViewById(R.id.IDFullname)
        etEmail = findViewById(R.id.IDEmail)
        etAddress = findViewById(R.id.IDAddress)
        etMobile = findViewById(R.id.IDMobile)
        etNIC = findViewById(R.id.IDNic)
        etPass = findViewById(R.id.IDPass)
        etConfPass = findViewById(R.id.IDConfoPass)
        btnTerms = findViewById(R.id.IDBtnTerms)
        btnSignUp = findViewById(R.id.IDBtnSignUp)
        //tvRedirectLogin = findViewById(R.id.Login)

        // Initialising auth object
        auth = Firebase.auth

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        // switching from signUp Activity to Login Activity
        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signUpUser() {
        val fullname = etFullname.text.toString()
        val email = etEmail.text.toString()
        val address = etAddress.text.toString()
        val mobile = etMobile.text.toString()
        val nic = etNIC.text.toString()
        val pass = etPass.text.toString()
        val confPass = etConfPass.text.toString()


        // check field blanks
        if (fullname.isBlank() || email.isBlank() || address.isBlank() || mobile.isBlank() || nic.isBlank() || pass.isBlank() || confPass.isBlank()  ) {
            Toast.makeText(this, "Every field should be filled", Toast.LENGTH_SHORT).show()
            return
        }


        //check password
        if (pass != confPass) {
            Toast.makeText(this, "Password and Password Confirmation is not same", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Sign up Successful.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Sign up Failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
