package com.eyepax.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.eyepax.newsapp.R
import com.eyepax.newsapp.databinding.ActivityHomeBinding
import com.eyepax.newsapp.repository.NewsRepository


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val newsRepository = NewsRepository()
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        val fragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)

        if (fragment != null) {
            binding.bottomNavigationView.setupWithNavController(fragment.findNavController())
        }


    }



}