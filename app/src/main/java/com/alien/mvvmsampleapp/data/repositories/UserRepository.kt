package com.alien.mvvmsampleapp.data.repositories

import android.util.Log
import com.alien.mvvmsampleapp.data.db.AppDatabase
import com.alien.mvvmsampleapp.data.db.entities.User
import com.alien.mvvmsampleapp.data.networks.MyApi
import com.alien.mvvmsampleapp.data.networks.SafeApiRequest
import com.alien.mvvmsampleapp.data.networks.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
): SafeApiRequest() {
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest {api.userLogin(email,password)}
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse{
        return apiRequest { api.userSignup(name,email,password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()

}
