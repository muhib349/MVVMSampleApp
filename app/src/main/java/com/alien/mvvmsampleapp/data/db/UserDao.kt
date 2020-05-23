package com.alien.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alien.mvvmsampleapp.data.db.entities.CURRENT_USER_ID
import com.alien.mvvmsampleapp.data.db.entities.User


interface UserDao {
    /*@Insert()
    fun upsert(user: User): Long

    @Query("Select * from user where uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>*/
}