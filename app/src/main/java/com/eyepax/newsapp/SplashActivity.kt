package com.eyepax.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.eyepax.newsapp.databinding.ActivityLoginBinding
import com.eyepax.newsapp.databinding.ActivitySplashBinding
import com.eyepax.newsapp.ui.HomeActivity
import com.eyepax.newsapp.util.Constants
import com.eyepax.newsapp.util.SharedPreferenceHelper

class SplashActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashBinding
    private val splashTime: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Handler(Looper.getMainLooper()).postDelayed({

            startApp()

        }, splashTime)

    }


    private fun startApp() {


        val isLoggedIn: Boolean = SharedPreferenceHelper.getInstance(applicationContext).getSharedPreferenceBoolean(
            Constants.IS_LOGGED_IN, false)


        if (isLoggedIn) {

            val mainIntent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(mainIntent)
            finish()

        } else {

            val loginIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()

        }

    }
}