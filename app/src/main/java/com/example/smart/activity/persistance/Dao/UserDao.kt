package com.example.smart.activity.persistance.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smart.activity.data.UserVO

@Dao
interface UserDao {
    @Query("SELECT * FROM UserVO")
    fun getAllUsers():List<UserVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetUserList(userList:List<UserVO>)
}