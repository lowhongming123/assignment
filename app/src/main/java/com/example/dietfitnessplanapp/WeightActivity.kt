package com.example.dietfitnessplanapp

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_weight.*
import kotlinx.android.synthetic.main.activity_weight.*

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.firebase.ui.database.ChangeEventListener
import com.firebase.ui.database.FirebaseRecyclerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.food_layout.view.*
import java.util.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.example.dietfitnessplanapp.FoodViewHolder
import androidx.recyclerview.widget.RecyclerView
import androidx.core.app.ComponentActivity





class WeightActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private val TAG = "WeightActivity"
    private val REQUIRED = "Required"

    //access database table
    private var foodDatabase: DatabaseReference? = null
    //to get the current database pointer
    private var foodReference: DatabaseReference? = null


    private var foodAdapter: FirebaseRecyclerAdapter<Food, FoodViewHolder>? = null

    private var mAuth: FirebaseAuth? = null
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    var _currentWeight : Int = 0
    var _expectedWeight : Int = 0
    var _email : String? = null
    var _username :String? = null
    var _userTarget : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)


        val _userId = intent.getStringExtra(MainActivity.KEY)
        val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(_userId!!)

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



        //to get the root folder
        foodDatabase = FirebaseDatabase.getInstance().reference
        //to access to the table
        foodReference = FirebaseDatabase.getInstance().getReference("Food")


        foodRecycleView2.layoutManager = LinearLayoutManager(this)
        //val query = foodReference!!.limitToLast(8)
        val query = foodReference!!.orderByChild("foodName")
        foodAdapter = object: FirebaseRecyclerAdapter<Food, FoodViewHolder>(
            Food::class.java, R.layout.food_layout, FoodViewHolder::class.java,query
        ){
            override fun populateViewHolder(viewHolder: FoodViewHolder?, model: Food?, position: Int) {

                if(_userTarget.equals("Lose Weight")){
                    if(model!!.foodCaloriesType.equals("Low")){
                        viewHolder!!.itemView.setVisibility(View.VISIBLE)
                        viewHolder!!.itemView.setLayoutParams(RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))

//                        viewHolder.setIsRecyclable(true)
                    }else{
                        viewHolder!!.itemView.setVisibility(View.GONE)
                        viewHolder!!.itemView.setLayoutParams(RecyclerView.LayoutParams(0, 0))
                    }
                }else if(_userTarget.equals("Gain Weight")){

                    if(model!!.foodCaloriesType.equals("High")){
                        viewHolder!!.itemView.setVisibility(View.VISIBLE)
                        viewHolder!!.itemView.setLayoutParams(RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))

//                        viewHolder.setIsRecyclable(true)
                    }else{
                        viewHolder!!.itemView.setVisibility(View.GONE)
                        viewHolder!!.itemView.setLayoutParams(RecyclerView.LayoutParams(0, 0))
                    }
                }else if(_userTarget.equals("Maintain Weight")){

                    if(model!!.foodCaloriesType.equals("Moderate")){
                        viewHolder!!.itemView.setVisibility(View.VISIBLE)
                        viewHolder!!.itemView.setLayoutParams(RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))

//                        viewHolder.setIsRecyclable(true)
                    }else{
                        viewHolder!!.itemView.setVisibility(View.GONE)
                        viewHolder!!.itemView.setLayoutParams(RecyclerView.LayoutParams(0, 0))
                    }
                }

                viewHolder!!.bindFood(model)
            }

            override fun onChildChanged(
                type: ChangeEventListener.EventType?,
                snapshot: DataSnapshot?,
                index: Int,
                oldIndex: Int
            ) {
                super.onChildChanged(type, snapshot, index, oldIndex)
                foodRecycleView2.scrollToPosition(index)
            }


        }
        foodRecycleView2.adapter = foodAdapter


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
        val _userId = intent.getStringExtra(MainActivity.KEY)

        when (item.itemId) {
            R.id.nav_help -> {
                val intent = Intent(this, CustomerService::class.java)
                startActivity(intent)
            }
            R.id.nav_weight -> {

            }
            R.id.nav_food -> {
                val intent = Intent(this, FoodActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_updateProfile -> {
                val intent = Intent(this, UpdateProfileFragment::class.java)
                intent.putExtra(KEY4,_userId)
                startActivity(intent)

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



    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
        foodAdapter!!.cleanup()
    }

    companion object{
        const val KEY4 = "com.example.dietfitnessplanapp.KEY4"

    }

}