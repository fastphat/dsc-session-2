package com.example.dsc_session_2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailTv = findViewById<TextView>(R.id.username)
        val passwordTv = findViewById<TextView>(R.id.password)

        val email = SharedPreference.getEmail(this)
        val password = SharedPreference.getPassword(this)
        emailTv.text = "Email: $email"
        passwordTv.text = "Password: $password"
    }

}