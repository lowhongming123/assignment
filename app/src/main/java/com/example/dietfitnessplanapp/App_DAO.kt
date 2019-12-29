package com.example.dietfitnessplanapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface App_DAO {

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    fun saveUser(user:User_Entity)

    @Query("select * from User_Entity")
    fun readUser():List<User_Entity>
}