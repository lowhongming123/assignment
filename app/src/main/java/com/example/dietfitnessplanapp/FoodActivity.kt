package com.example.dietfitnessplanapp

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.ChangeEventListener
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_food.*
import kotlinx.android.synthetic.main.food_layout.view.*
import java.util.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.dietfitnessplanapp.FoodViewHolder




class FoodActivity : AppCompatActivity() {

    private val TAG = "FoodActivity"
    private val REQUIRED = "Required"

    //access database table
    private var foodDatabase: DatabaseReference? = null
    //to get the current database pointer
    private var foodReference: DatabaseReference? = null

    private var foodAdapter: FirebaseRecyclerAdapter<Food, FoodViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        //to get the root folder
        foodDatabase = FirebaseDatabase.getInstance().reference
        //to access to the table
        foodReference = FirebaseDatabase.getInstance().getReference("Food")


        foodRecycleView.layoutManager = LinearLayoutManager(this)
        //val query = foodReference!!.limitToLast(8)
        val query = foodReference!!.orderByChild("foodName")
        foodAdapter = object: FirebaseRecyclerAdapter<Food, FoodViewHolder>(
            Food::class.java, R.layout.food_layout, FoodViewHolder::class.java,query
        ){
            override fun populateViewHolder(viewHolder: FoodViewHolder?, model: Food?, position: Int) {
                viewHolder!!.bindFood(model)
            }

            override fun onChildChanged(
                type: ChangeEventListener.EventType?,
                snapshot: DataSnapshot?,
                index: Int,
                oldIndex: Int
            ) {
                super.onChildChanged(type, snapshot, index, oldIndex)
                foodRecycleView.scrollToPosition(index)
            }
            //this one no need because no need start intent to view the location

        }
        foodRecycleView.adapter = foodAdapter

    }


    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
        foodAdapter!!.cleanup()
    }
}