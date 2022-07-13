package com.example.task1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.task1.database.RoomDb
import com.example.task1.database.UserEntity
import kotlinx.android.synthetic.main.activity_work_manager_demo.*

class WorkManagerDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager_demo)
        saveDatainRoomDb()
    }

    fun saveDatainRoomDb(){
        submitBTNID.setOnClickListener {

            val name = nameETID.text.toString()
            val phone = phoneETID.text.toString()
            val email = emailETID.text.toString()
            val userDao = RoomDb.getAppDateBase(this)?.userDao()
            val userEntity = UserEntity(0, name, phone, email)
            userDao?.insertUser(userEntity)
            Toast.makeText(this, "Submitted Sucessfully", Toast.LENGTH_LONG).show()
        }

        dataBTNID.setOnClickListener {
                startActivity(Intent(this, DisplayData::class.java))
            }

    }
}