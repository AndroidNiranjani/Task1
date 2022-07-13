package com.example.task1.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDetails")
data class UserEntity(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int,
                       @ColumnInfo(name = "userName") val username:String,
                        @ColumnInfo(name="userphone") val userphone:String,
                        @ColumnInfo(name="userEmail") val userEmail:String
                        )