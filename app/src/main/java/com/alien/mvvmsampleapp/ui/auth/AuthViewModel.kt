package com.alien.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.alien.mvvmsampleapp.data.repositories.UserRepository
import com.alien.mvvmsampleapp.utils.ApiException
import com.alien.mvvmsampleapp.utils.Coroutines

class AuthViewModel(
    private val repository: UserRepository
): ViewModel() {
    var email: String? = "probelalkhan@gmail.com"
    var password: String? = "123456"
    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Wrong email or password")
            return
        }
        Coroutines.main {
            try{
                val response = repository.userLogin(email!!,password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(response?.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }

        }
    }
}