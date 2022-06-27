package com.eyepax.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eyepax.newsapp.databinding.ActivityLoginBinding
import com.eyepax.newsapp.ui.HomeActivity
import com.eyepax.newsapp.util.Constants
import com.eyepax.newsapp.util.SharedPreferenceHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnSignIn.setOnClickListener{

            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty()) {
                binding.etUsername.error = "Username required"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            }


            if (password.isEmpty()) {
                binding.etPassword.error = "Password required"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            SharedPreferenceHelper.getInstance(applicationContext).setSharedPreferenceBoolean(
                Constants.IS_LOGGED_IN, true)

            val intent = Intent(applicationContext, HomeActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

        }


        binding.loSignUp.setOnClickListener{

            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}