package com.example.dietfitnessplanapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")

data class User_Entity (
    @PrimaryKey(autoGenerate = true) val user_id:Int,
    var username:String,
    var password:String
)




