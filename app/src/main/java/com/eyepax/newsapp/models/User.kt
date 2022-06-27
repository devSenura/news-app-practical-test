package com.eyepax.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "users"
)
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val name: String?,
    val username: String?,
    var country: String?,
    val language: String?,
    val email: String?,
    val password: String?,
) : Serializable