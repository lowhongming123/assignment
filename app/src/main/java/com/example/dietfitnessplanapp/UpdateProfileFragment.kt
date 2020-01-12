package com.example.dietfitnessplanapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.MenuItem
import kotlinx.android.synthetic.main.content_update.*

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.text.Editable



class UpdateProfileFragment : AppCompatActivity(){

    private val TAG = "UpdateProfileFragment"
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_update)

        
        val _userId = intent.getStringExtra(WeightActivity.KEY4)
        val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(_userId)


        databaseReference.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                editTextName.text = dataSnapshot.child("username").getValue().toString().toEditable()
                editTextGender.text = dataSnapshot.child("gender").getValue().toString().toEditable()
                editTextEmail.text = dataSnapshot.child("email").getValue().toString().toEditable()
                editTextCountry.text = dataSnapshot.child("country").getValue().toString().toEditable()
                editTextCurrentWeight.text = dataSnapshot.child("currentweight").getValue().toString().toEditable()
                editTextExpectedWeight.text = dataSnapshot.child("expectedweight").getValue().toString().toEditable()

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

        buttonEdit.setOnClickListener {
            updateUserProfile()
        }

    }


    private fun updateUserProfile() {

        val _userId = intent.getStringExtra(WeightActivity.KEY4)
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference().child("user")


        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

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
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)


}
