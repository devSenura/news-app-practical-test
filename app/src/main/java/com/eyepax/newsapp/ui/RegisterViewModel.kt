package com.eyepax.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyepax.newsapp.models.User
import com.eyepax.newsapp.repository.RegisterRepository
import kotlinx.coroutines.launch


class RegisterViewModel(
    val registerRepository: RegisterRepository
) : ViewModel() {


    fun registerUser(user: User) = viewModelScope.launch {
        registerRepository.insert(user)
    }

    fun getUserByUsername(name: String) = viewModelScope.launch {
        registerRepository.getUserByUserName(name)
    }

}