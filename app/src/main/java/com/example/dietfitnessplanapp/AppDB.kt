package com.example.dietfitnessplanapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [(User_Entity::class)],version = 1, exportSchema = false)
abstract class AppDB:RoomDatabase(){
    abstract fun appDAO():App_DAO
}

