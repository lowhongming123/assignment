package com.example.dietfitnessplanapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME="Users"
val COL_ID="id"
val COL_USERNAME="username"
val COL_EMAIL="email"
val COL_PASSWORD="password"

class DataBaseHandler(var context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable="CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_USERNAME + " VARCHAR(256)," +
                COL_EMAIL + " VARCHAR(256)," +
                COL_PASSWORD + " VARCHAR(256) )"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertDate(user:User){
        val db= this.writableDatabase
        var cv=ContentValues()
        cv.put(COL_USERNAME,user.username)
        cv.put(COL_EMAIL,user.email)
        cv.put(COL_PASSWORD,user.password)
        var result = db.insert(TABLE_NAME,null,cv)

        if(result == -1.toLong())
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show()

        else
            Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()

    }

    fun readData(): MutableList<User>{
        var list: MutableList<User> = ArrayList()

        val db=this.readableDatabase
        val query="Select * from " + TABLE_NAME
        val result=db.rawQuery(query,null)

        if(result.moveToFirst()){
            do{

                var user=User()
                user.id=result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.username=result.getString(result.getColumnIndex(COL_USERNAME))
                user.email=result.getString(result.getColumnIndex(COL_EMAIL))
                user.password=result.getString(result.getColumnIndex(COL_PASSWORD))
                list.add(user)

            }while(result.moveToNext())
        }
            result.close()
        db.close()

        return list
    }

    /*  This example is just clicking update button to keep increasing the number of age by 1

    fun updateData(){

        val db=this.writableDatabase
        val query="Select * from " + TABLE_NAME
        val result=db.rawQuery(query,null)

        if(result.moveToFirst()){
            do{
                var cv=ContentValues()
                cv.put(COL_PASSWORD,result.get(result.getColumnIndex(COL_AGE))+1)

                db.update(TABLE_NAME,cv, COL_ID + "=?  AND " + COL_USERNAME + "+? AND " + COL_EMAIL + "+?",
                    arrayOf(result.getString(result.getColumnIndex(COL_ID)),
                        result.getString(result.getColumnIndex(COL_USERNAME)),
                        result.getString(result.getColumnIndex(COL_EMAIL))))

            }while(result.moveToNext())
        }

        result.close()
        db.close()

    }
    */


    fun deleteData(){
        val db=this.writableDatabase
        db.delete(TABLE_NAME, COL_ID+"=?", arrayOf(1.toString()))
        //IF WANT TO DELETE ALL THE RECORD
            //db.delete(TABLE_NAME, null,null)
        db.close()
    }
}
