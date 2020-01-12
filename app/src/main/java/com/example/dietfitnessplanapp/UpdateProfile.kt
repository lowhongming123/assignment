package com.example.dietfitnessplanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.util.Log
import android.text.TextUtils
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.content_update.*

import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ListView
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.nav_header.*

import androidx.core.view.accessibility.AccessibilityRecordCompat
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.dietfitnessplanapp.User
import android.text.Editable



class UpdateProfile : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private val TAG = "UpdateProfile"
    private var mAuth: FirebaseAuth? = null
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val _userId = intent.getStringExtra(MainActivity.KEY)
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

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser


        when (item.itemId) {
            R.id.nav_help -> {
                Toast.makeText(this, "Help clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_weight -> {

            }
            R.id.nav_diary -> {
                Toast.makeText(this, "Diary clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_updateProfile -> {
                updateUserProfile()
            }
            R.id.nav_logout -> {
                mAuth!!.signOut()
                Toast.makeText(this, "Signed out...", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun updateUserProfile() {

        val _userId = intent.getStringExtra(MainActivity.KEY)
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
