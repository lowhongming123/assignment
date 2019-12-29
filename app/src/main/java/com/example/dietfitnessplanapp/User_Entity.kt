package com.example.dietfitnessplanapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User_Entity {

    @PrimaryKey
    var user_id:Int=0

    @ColumnInfo(name="USERNAME")
    var username:String=""

    @ColumnInfo(name="PASSWORD")
    var password:String=""
}