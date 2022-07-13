package com.example.task1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Query("SELECT * FROM userDetails ORDER BY id desc")
    fun getUserData(): List<UserEntity>?

    @Insert
    fun insertUser(user:UserEntity) : Long
}