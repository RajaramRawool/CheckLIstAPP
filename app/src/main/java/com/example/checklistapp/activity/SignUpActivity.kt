package com.example.checklistapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.checklistapp.appdatabase.Api
import com.example.checklistapp.databinding.ActivitySignUpBinding
import com.example.checklistapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()


    }

    private fun setListeners() {
        binding.btSignup.setOnClickListener{
            val call = Api.retrofitService.insertUser("Rajaram","rajaram903@gmail.com","1234")
            call.enqueue(object : Callback<User?> {
                override fun onResponse(call: Call<User?>, response: Response<User?>) {
                    Toast.makeText(this@SignUpActivity,"Success",Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<User?>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity,t.message,Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}