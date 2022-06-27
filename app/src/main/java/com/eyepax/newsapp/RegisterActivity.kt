package com.eyepax.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eyepax.newsapp.databinding.ActivityRegisterBinding
import com.eyepax.newsapp.db.UserDatabase
import com.eyepax.newsapp.models.User
import com.eyepax.newsapp.repository.RegisterRepository
import com.eyepax.newsapp.ui.HomeActivity
import com.eyepax.newsapp.ui.RegisterViewModel
import com.eyepax.newsapp.ui.RegisterViewModelProviderFactory
import kotlinx.coroutines.Job

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: RegisterViewModel

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val registerRepository = RegisterRepository(UserDatabase(this))
        val viewModelProviderFactory = RegisterViewModelProviderFactory(registerRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(RegisterViewModel::class.java)



        val user = viewModel.getUserByUsername("senura")

        binding.btnSignUp.setOnClickListener{
            //viewModel.registerUser(User(1,"Senura","senura","Sri Lanka", "Sinhala","senuradiwantha900@gmail.com","BlaBla"))

            val mainIntent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(mainIntent)
            finish()
        }

        binding.loSignIn.setOnClickListener{

            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}