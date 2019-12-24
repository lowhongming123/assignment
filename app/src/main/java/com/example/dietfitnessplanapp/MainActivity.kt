package com.example.dietfitnessplanapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        buttonSignIn.setOnClickListener(){
            login();

        }


        // get reference to textview
        val textView_clickable = findViewById(R.id.textViewGuidance) as TextView

        textView_clickable.setOnClickListener {

            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
            // Toast.makeText(this@MainActivity, "You clicked on sign up ", Toast.LENGTH_SHORT).show()
            //register();
        }
    }

    private fun login(){

    /*
        val intent= Intent(Intent.ACTION_VIEW)
        val phone:String = "tel :0123456789"

        Check package manager for app to handle an intent
       intent.setData(Uri.parse(phone))
        if(intent.resolveActivity(packageManager) !== null){

        }
        */

        val intent = Intent(this,WeightActivity::class.java)
        startActivity(intent)

    }

 /*
    private fun register(){


        Explicit
        val intent = Intent(this,SecondActivity::class.java)


        val username= editTextUsername.text.toString()
        val password=editTextPassword.text.toString()
        intent.putExtra(KEY,username)
        intent.putExtra(KEY2,password)

         startActivity(intent)  // An intent without return value
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

*/




