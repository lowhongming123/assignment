package com.example.dietfitnessplanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import android.widget.Toast
import android.view.View
import android.util.Log
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.example.dietfitnessplanapp.User


class MainActivity : AppCompatActivity(),View.OnClickListener {

    private var mAuth: FirebaseAuth? = null
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSignIn.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()

        // get reference to textview
        val textView_clickable = findViewById(R.id.textViewGuidance) as TextView

        textView_clickable.setOnClickListener {
            register();
        }
    }


    override fun onClick(view: View?) {
        val i = view!!.id

        when (i) {
            R.id.buttonSignIn -> login(editTextEmail.text.toString(),editTextPassword.text.toString())
        }
    }

    private fun login(email: String, password: String) {
        Log.e(TAG, "signIn:" + email)
        if (!validateForm(email, password)) {
            return
        }

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.e(TAG, "signIn: Success!")

                    // update UI with the signed-in user's information
                    val user = mAuth!!.currentUser
                    Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, WeightActivity::class.java)
                    intent.putExtra(KEY,email)
                    startActivity(intent)


                } else {
                    Log.e(TAG, "signIn: Fail!", task.exception)
                    Toast.makeText(applicationContext, "Invalid email or password!!", Toast.LENGTH_SHORT).show()
                }


            }
    }


    private fun validateForm(email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email address!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password!", Toast.LENGTH_SHORT).show()
            return false
        }


        return true
    }




    private fun register() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    companion object{
        const val KEY = "com.example.dietfitnessplanapp.KEY"

    }


}





