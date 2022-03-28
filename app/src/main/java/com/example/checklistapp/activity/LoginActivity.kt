package com.example.checklistapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.checklistapp.appdatabase.Api
import com.example.checklistapp.databinding.ActivityLoginBinding
import com.example.checklistapp.model.ResponseMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.checklistapp.Message
import com.example.checklistapp.util.AppSharedPreference

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sf : AppSharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sf = AppSharedPreference(this@LoginActivity)

        if(sf.getUserSession()) {
            startActivity(Intent(this@LoginActivity,CheckListActivity::class.java))
        }

//        SetListeners
        setListeners()
    }
    private fun setListeners() {
//    process for login
        binding.btLogin.setOnClickListener{
            var email = binding.etEmail.text.toString().trim()
            var password = binding.etPassword.text.toString().trim()
            if (userInputValidation(email,password)) {
                val call = Api.retrofitService.userLogin(email,password)
                call.enqueue(object : Callback<ResponseMessage?> {
                    override fun onResponse(
                        call: Call<ResponseMessage?>,
                        response: Response<ResponseMessage?>
                    ) {
                        var message = response.body()?.message
                        when(message) {
                            Message.SUCCESSFULLY_LOGIN -> {
                                Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG)
                                    .show()
                                sf.setUserSession(true)
                                startActivity(Intent(this@LoginActivity,CheckListActivity::class.java))
                            }
                            Message.WRONG_CREDENTIALS , Message.SOMETHING_WENT_WRONG -> Toast.makeText(this@LoginActivity,message,Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<ResponseMessage?>, t: Throwable) {
                    }
                })
            }

        }


//    navigate to Signup page
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java ))
        }
    }


    private fun userInputValidation(
        email: String,
        password: String
    ): Boolean {
        if (isValidEmail(email)
            && isValidPassword(password)

        ) {
            return true
        }
        return false
    }


    private fun isValidPassword(password: String): Boolean {
        var result = false

        when {
            password.isEmpty() -> binding.etPassword.error = "Enter Password"

            (password.length < 4) || (password.length > 8) -> binding.etPassword.error =
                "Length Should be between 4 to 8"

            else -> result = true
        }
        return result

    }

    private fun isValidEmail(email: String): Boolean {
        var result = false
        when {
            email.isEmpty() -> binding.etEmail.error = "Enter Email"

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> binding.etEmail.error =
                "Enter Valid Email"

            else -> result = true
        }
        return result
    }

}