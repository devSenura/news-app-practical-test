package com.eyepax.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eyepax.newsapp.repository.RegisterRepository

class RegisterViewModelProviderFactory(
    val registerRepository: RegisterRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(registerRepository) as T
    }
}