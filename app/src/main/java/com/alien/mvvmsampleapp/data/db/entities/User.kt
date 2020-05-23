package com.alien.mvvmsampleapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    val created_at: String? = null,
    val email: String? = null,
    val email_verified_at: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val updated_at: String? = null
){
    @PrimaryKey(autoGenerate = false)
    val uid: Int = CURRENT_USER_ID
}