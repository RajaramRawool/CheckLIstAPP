package com.example.checklistapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.checklistapp.Message
import com.example.checklistapp.appdatabase.Api
import com.example.checklistapp.databinding.ActivitySignUpBinding
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

//      process for signup
        binding.btSignup.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            if (userInputValidation(name, email, password, confirmPassword)) {

                val call = Api.retrofitService.userRegistration(name, email, password)
                call.enqueue(object : Callback<com.example.checklistapp.model.ResponseMessage> {
                    override fun onResponse(
                        call: Call<com.example.checklistapp.model.ResponseMessage>,
                        responseMessage: Response<com.example.checklistapp.model.ResponseMessage>
                    ) {
                        var message = responseMessage.body()?.message
                        when (message) {
                            Message.USER_ALREADY_EXIST, Message.SOMETHING_WENT_WRONG , Message.FAILED_TO_ADD_USER -> {
                                binding.etName.text.clear()
                                binding.etEmail.text.clear()
                                binding.etPassword.text.clear()
                                binding.etConfirmPassword.text.clear()

                                Toast.makeText(this@SignUpActivity, message, Toast.LENGTH_LONG)
                                    .show()

                            }
                            Message.USER_ADDED_SUCCESSFULLY -> {
                                startActivity(
                                    Intent(
                                        this@SignUpActivity,
                                        LoginActivity::class.java
                                    )
                                )
                                Toast.makeText(this@SignUpActivity, message, Toast.LENGTH_LONG)
                                    .show()
                            }
                        }


                    }

                    override fun onFailure(
                        call: Call<com.example.checklistapp.model.ResponseMessage?>,
                        t: Throwable
                    ) {
                        Toast.makeText(this@SignUpActivity, t.message.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                })
            }
        }
    }


    private fun userInputValidation(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (isValidName(name)
            && isValidEmail(email)
            && isValidPassword(password)
            && isValidConfirmPassword(password, confirmPassword)
        ) {
            return true
        }
        return false
    }
    private fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
        var result = false

        when {
            confirmPassword.isEmpty() -> binding.etConfirmPassword.error =
                "Repeat Password to Confirm"

            password != confirmPassword -> binding.etConfirmPassword.error =
                "Password doesn't match"

            else -> result = true
        }
        return result
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

    private fun isValidName(name: String): Boolean {
        var result = false
        when {
            name.isEmpty() -> binding.etName.error = "Enter name"

            (name.length < 4) || (name.length > 8) -> binding.etName.error =
                "Length should be between 4 to 8"

            else -> result = true
        }
        return result
    }

}