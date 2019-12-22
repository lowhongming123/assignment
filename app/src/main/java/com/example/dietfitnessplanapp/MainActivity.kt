package com.example.dietfitnessplanapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSignIn.setOnClickListener(){
            login();

        }
        textViewGuidance.setOnClickListener(){
            register();
        }

    }

    private fun login(){
        val intent= Intent(Intent.ACTION_VIEW)
        val phone:String = "tel :0123456789"

        //Check package manager for app to handle an intent
        intent.setData(Uri.parse(phone))
        if(intent.resolveActivity(packageManager) !== null){
            startActivity(intent)
        }


    }


    private fun register(){
        //Explicit
        val intent = Intent(this,SecondActivity::class.java)

        //val message=editTextMessage.text.toString()
        val number= editTextLuckyNumber.text.toString().toInt()
        intent.putExtra(KEY,message)
        intent.putExtra(KEY2,number)

        // startActivity(intent)  // An intent without return value
        startActivityForResult(intent,REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode== REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                val reply=data?.getStringExtra(KEY3).toString()
                textViewReply.text=String.format(" %s  %s ",getString(R.string.reply),reply)
            }
        }
    }



    companion object{
        const val KEY = "com.example.myapplication.KEY"
        const val KEY2 = "com.example.myapplication.KEY2"
        const val KEY3 = "com.example.myapplication.KEY3"
        const val REQUEST_CODE= 1
    }



}
