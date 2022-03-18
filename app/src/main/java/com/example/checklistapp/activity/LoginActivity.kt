package com.example.checklistapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.checklistapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        SetListeners
        setListeners()
    }

    private fun setListeners() {
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java ))
        }
    }
}