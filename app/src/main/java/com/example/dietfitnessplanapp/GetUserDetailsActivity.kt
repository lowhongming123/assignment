package com.example.dietfitnessplanapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*
import android.R
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Spinner
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class GetUserDetailsActivity : AppCompatActivity() {

//    private val db = FirebaseDatabase.getInstance()
//    private val users = db.getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.dietfitnessplanapp.R.layout.activity_details)

        val context=this
//        var db=DataBaseHandler(context)

//        val username = intent.getStringExtra(SecondActivity.KEY)
//        textViewWelcome.text= String.format("Welcome , %s ",username)

        buttonSubmit.setOnClickListener {

            if(editTextWeight.text.isEmpty()||editTextWeight2.text.isEmpty()||editTextHeight.text.isEmpty()){
                Toast.makeText(context,"Please fill in the form!!!",Toast.LENGTH_SHORT).show()

            }else{

//                val _username = intent.getStringExtra(SecondActivity.KEY3)
//                val _email = intent.getStringExtra(SecondActivity.KEY4)
//                val _password = intent.getStringExtra(SecondActivity.KEY5)

                // Get the clicked radio button instance
                val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
                val _userTarget=radio.text.toString()

                val _expectedWeight=editTextWeight.text.toString()
                val radio2:RadioButton=findViewById(radioGroup2.checkedRadioButtonId)
                val _gender=radio2.text.toString()

                val _currentWeight=editTextWeight2.text.toString()
                val _currentHeight=editTextHeight.text.toString()

                val mySpinner = findViewById(com.example.dietfitnessplanapp.R.id.spinner) as Spinner
                val _country = mySpinner.getSelectedItem().toString()

//                val user = User(username=_username,email = _email,password = _password,userTarget = _userTarget,
//                    expectedWeight = _expectedWeight.toInt(),gender = _gender,currentWeight = _currentWeight.toInt(),
//                    currentHeight = _currentHeight.toInt(),country = _country)

//                db.insertData(user)

//                users.child(_username).setValue(user)


                finish()
            }

        }


    }




}