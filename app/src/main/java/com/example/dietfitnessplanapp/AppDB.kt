package com.example.dietfitnessplanapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(User_Entity::class),version = 1)
public abstract class AppDB:RoomDatabase(){

    abstract fun appDAO():App_DAO

    companion object{

        @Volatile
        private var INSTANCE : AppDB?=null

        fun getDatabase(context: Context):AppDB{
            var tempDB= INSTANCE
            if(tempDB!=null){
                return tempDB
            }

            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "user_db"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}

