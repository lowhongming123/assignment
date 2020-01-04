package com.example.dietfitnessplanapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_second.*
import android.R
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class GetUserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val username = intent.getStringExtra(SecondActivity.KEY)
        textViewWelcome.text= String.format("Welcome , %s ",username)

        buttonSubmit.setOnClickListener {
            getUserInfo()
        }


    }

    private fun getUserInfo(){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
        val userTarget=radio.text.toString()

        val expectedWeight=editTextWeight.text.toString()
        val radio2:RadioButton=findViewById(radioGroup2.checkedRadioButtonId)
        val gender=radio2.text.toString()

        val currentWeight=editTextWeight2.text.toString()
        val currentHeight=editTextHeight.text.toString()

        val mySpinner = findViewById(R.id.spinner) as Spinner
        val country = mySpinner.getSelectedItem().toString()

        finish()

    }


}