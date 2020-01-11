package com.example.dietfitnessplanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.util.Log
import android.text.TextUtils
import androidx.core.view.accessibility.AccessibilityRecordCompat
import kotlinx.android.synthetic.main.activity_update.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class UpdateProfileActivity : AppCompatActivity(){

    private val TAG = "UpdateProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val _userId = intent.getStringExtra(MainActivity.KEY)
        val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(_userId)


        databaseReference.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                editTextName.text = dataSnapshot.child("username").getValue().toString()
                editTextGender.text = dataSnapshot.child("gender").getValue().toString()
                editTextEmail.text = dataSnapshot.child("email").getValue().toString()
                editTextCountry.text = dataSnapshot.child("country").getValue().toString()
                editTextCurrentWeight.text = dataSnapshot.child("currentweight").getValue().toString()
                editTextExpectedWeight.text = dataSnapshot.child("expectedweight").getValue().toString()

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

        buttonEdit.setOnClickListener {
            updateUserProfile()
        }
    }


    private fun updateUserProfile() {

        val _userId = intent.getStringExtra(MainActivity.KEY)
        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference().child("user").child(_userId)


        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try {

                    databaseReference.child(_userId).child("username")
                        .setValue(editTextName.text.toString())
                    databaseReference.child(_userId).child("gender")
                        .setValue(editTextGender.text.toString())
                    databaseReference.child(_userId).child("email")
                        .setValue(editTextEmail.text.toString())
                    databaseReference.child(_userId).child("country")
                        .setValue(editTextCountry.text.toString())
                    databaseReference.child(_userId).child("currentweight")
                        .setValue(editTextCurrentWeight.text.toString())
                    databaseReference.child(_userId).child("expectedweight")
                        .setValue(editTextExpectedWeight.text.toString())

                    Toast.makeText(
                        applicationContext,
                        "User profile updated successfully...",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

}
