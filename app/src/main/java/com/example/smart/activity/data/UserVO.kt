package com.example.smart.activity.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserVO(
    @PrimaryKey
    val name:String, val phone:String, val email:String, val address:String)