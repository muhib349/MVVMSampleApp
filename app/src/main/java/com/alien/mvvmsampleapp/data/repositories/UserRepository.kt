package com.alien.mvvmsampleapp.data.repositories

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

}
