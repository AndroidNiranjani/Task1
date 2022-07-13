package com.example.task1.database

import android.content.Context
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1, exportSchema = true)
abstract class RoomDb: RoomDatabase() {

    abstract fun userDao():UserDao?
    companion object {
        private var INSTANCE: RoomDb? = null

        fun getAppDateBase(context: Context): RoomDb? {
            if (INSTANCE == null) {

                INSTANCE= Room.databaseBuilder<RoomDb>(
                    context.applicationContext, RoomDb::class.java,"demo_dp"
                ).allowMainThreadQueries().build()

            }
            return INSTANCE
        }
    }

}