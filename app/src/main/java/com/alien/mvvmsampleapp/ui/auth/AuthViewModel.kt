package com.alien.mvvmsampleapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.alien.mvvmsampleapp.data.repositories.UserRepository
import com.alien.mvvmsampleapp.utils.ApiException
import com.alien.mvvmsampleapp.utils.Coroutines
import com.alien.mvvmsampleapp.utils.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
): ViewModel() {
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirmed: String? = null

    var authListener: AuthListener? = null

    val loggedInUser = repository.getUser()

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
                    repository.saveUser(it)
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(response.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }
    fun onSignup(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view: View){
        authListener?.onStarted()
        if (name.isNullOrEmpty()){
            authListener?.onFailure("Name is required!")
            return
        }
        if(email.isNullOrEmpty()){
            authListener?.onFailure("Email is required!")
            return
        }
        if(password.isNullOrEmpty()){
            authListener?.onFailure("Password is required!")
            return
        }
        if(password != passwordConfirmed){
            authListener?.onFailure("Password didn't match")
            return
        }

        Coroutines.main {
            try{
                val response = repository.userSignup(name!!, email!!,password!!)
                response.user?.let {
                    repository.saveUser(it)
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(response.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }
    }
}