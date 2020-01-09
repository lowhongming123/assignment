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

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.example.dietfitnessplanapp.User
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class GetUserDetailsActivity : AppCompatActivity() {

    private val TAG = "GetUserDetailsActivity"

    //access database table
    private var userDatabase: DatabaseReference? = null
    //to get current database pointer
    private var userReference: DatabaseReference? = null

    //read data
    private var userListener:ChildEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.dietfitnessplanapp.R.layout.activity_details)


        //to get the root folder
        userDatabase = FirebaseDatabase.getInstance().reference
        //to access to the table
        userReference = FirebaseDatabase.getInstance().getReference("user")


        val _username = intent.getStringExtra(SecondActivity.KEY1)
        textViewWelcome.text = String.format("Welcome , %s ", _username)

        buttonSubmit.setOnClickListener {

            if (editTextWeight.text.isEmpty() || editTextWeight2.text.isEmpty() || editTextHeight.text.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill in the form!!!", Toast.LENGTH_SHORT)
                    .show()

            } else {

                val _email = intent.getStringExtra(SecondActivity.KEY2)

                // Get the clicked radio button instance
                val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
                val _userTarget = radio.text.toString()

                val _expectedWeight = editTextWeight.text.toString()
                val radio2: RadioButton = findViewById(radioGroup2.checkedRadioButtonId)
                val _gender = radio2.text.toString()

                val _currentWeight = editTextWeight2.text.toString()
                val _currentHeight = editTextHeight.text.toString()

                val mySpinner = findViewById(com.example.dietfitnessplanapp.R.id.spinner) as Spinner
                val _country = mySpinner.getSelectedItem().toString()


                val user = User(
                    _username,
                    _email,
                    _userTarget,
                    _expectedWeight.toInt(),
                    _gender,
                    _currentWeight.toInt(),
                    _currentHeight.toInt(),
                    _country
                )
                val userValues = user.toMap()
                val childUpdates = HashMap<String, Any>()
                val key = userDatabase!!.child("user").push().key

                childUpdates.put("/user/" + key, userValues)

                userDatabase!!.updateChildren(childUpdates)
                Toast.makeText(applicationContext, "Register successfully!!", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }

        }


    }


}