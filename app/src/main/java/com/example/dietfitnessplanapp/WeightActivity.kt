package com.example.dietfitnessplanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_weight.*
import kotlinx.android.synthetic.main.content_weight.*

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.dietfitnessplanapp.User
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ListView
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.nav_header.*

class WeightActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private val TAG = "WeightActivity"
    private var mAuth: FirebaseAuth? = null
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    var _currentWeight : Int = 0
    var _expectedWeight : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)


        val _userId = intent.getStringExtra(MainActivity.KEY)
        val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(_userId)
        var _email : String? = null
        var _username :String? = null
        var _userTarget : String? = null
        var _goal : Int? = 0

        databaseReference.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                _currentWeight = dataSnapshot.child("currentweight").getValue().toString().toInt()
                _expectedWeight = dataSnapshot.child("expectedweight").getValue().toString().toInt()
                _userTarget = dataSnapshot.child("usertarget").getValue().toString()
                _email = dataSnapshot.child("email").getValue().toString()
                _username = dataSnapshot.child("username").getValue().toString()


                textViewCurrentWeightValue.text = String.format("%s KG",  _currentWeight.toString())
                textViewStartValue.text = String.format("%s KG",  _currentWeight.toString())
                textViewGoalValue.text = String.format("%s KG",  _expectedWeight.toString())

                textViewUserProfileEmail.text = _email.toString()
                textViewUserProfileName.text = _username.toString()
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })


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
                moveToUpdate()
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

    private fun moveToUpdate(){
        val intent = Intent(this, UpdateProfile::class.java)
        startActivity(intent)
    }

}