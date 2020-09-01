package com.example.smart.activity.persistance.Dao.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smart.activity.data.UserVO
import com.example.smart.activity.persistance.Dao.UserDao

@Database(
    entities = [UserVO::class],
    version = 1,
    exportSchema = false
)
abstract class UserDB:RoomDatabase() {
    companion object{
        val DB_NAME = "User.DB"
        var dbInstance:UserDB ?= null
        fun getDBInstance(context: Context):UserDB{
            when(dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context,UserDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            val i = dbInstance
            return i!!
        }
    }
    abstract fun userDao():UserDao
}