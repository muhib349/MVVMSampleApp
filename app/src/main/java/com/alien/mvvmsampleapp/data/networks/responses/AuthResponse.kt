package com.alien.mvvmsampleapp.data.networks.responses

import com.alien.mvvmsampleapp.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)