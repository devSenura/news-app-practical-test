package com.eyepax.newsapp.repository

import com.eyepax.newsapp.db.UserDatabase
import com.eyepax.newsapp.models.User


class RegisterRepository(
    val db: UserDatabase
) {

    suspend fun insert(user: User) = db.getUserDao().insert(user)

    suspend fun getUserByUserName(name: String) = db.getUserDao().getUserByUserName(name)


}