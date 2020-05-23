package com.alien.mvvmsampleapp.ui.auth

import com.alien.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User?)
    fun onFailure(message: String)
}