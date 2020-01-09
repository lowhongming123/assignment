package com.example.dietfitnessplanapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_weight.*
import kotlinx.android.synthetic.main.content_main.*

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.dietfitnessplanapp.User


import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T






class WeightActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private val TAG = "WeightActivity"



    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)





//        textViewStartValue.text= String.format("%s KG",expectedWeight)
//        textViewCurrentWeightValue.text= String.format("%s KG",expectedWeight)
//        textViewGoalValue.text= String.format("%s KG",currentWeight)

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
        when (item.itemId) {
            R.id.nav_help -> {
                Toast.makeText(this, "Help clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_weight -> {
                Toast.makeText(this, "Weight clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_diary -> {
                Toast.makeText(this, "Diary clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_updateProfile -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



}