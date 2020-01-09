package com.example.dietfitnessplanapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.util.Log
import android.text.TextUtils
import androidx.core.view.accessibility.AccessibilityRecordCompat
import kotlinx.android.synthetic.main.activity_second.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.example.dietfitnessplanapp.User
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference


class SecondActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "SecondActivity"

    //access database table
    private var userDatabase: DatabaseReference? = null
    //to get current database pointer
    private var userReference: DatabaseReference? = null

    //read data
    private var userListener:ChildEventListener? = null

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //to get the root folder
        userDatabase = FirebaseDatabase.getInstance().reference
        //to access to the table
        userReference = FirebaseDatabase.getInstance().getReference("user")


        buttonRegister.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()


    }


    override fun onClick(view: View?) {
        val i = view!!.id

        when (i) {
            R.id.buttonRegister -> register(editTextEmail.text.toString(),editTextPassword.text.toString())
        }
    }

    private fun register(email: String, password: String) {

        if (!validateForm(email, password)) {
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    // update UI with the signed-in user's information
                    val userAuth = mAuth!!.currentUser
                    writeNewUser(userAuth!!.uid, getUsernameFromEmail(userAuth.email), userAuth.email)

                    val user = User("Ming",userAuth.email)

                    val userValues = user.toMap()
                    val childUpdates = HashMap<String,Any>()
                    val key = userDatabase!!.child("user").push().key

                    childUpdates.put("/user/" + key, userValues)

                    userDatabase!!.updateChildren(childUpdates)

                    Toast.makeText(applicationContext, "Register successfully!", Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(applicationContext, "Failed to register!!", Toast.LENGTH_SHORT).show()
                }
            }


    }

    private fun validateForm(email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(applicationContext, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun writeNewUser(userId: String, username: String?, email: String?) {
        val user = User(username, email)

        FirebaseDatabase.getInstance().reference.child("user").child(userId).setValue(user)
    }

    private fun getUsernameFromEmail(email: String?): String {
        return if (email!!.contains("@")) {
            email.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        } else {
            email
        }
    }




//        buttonRegister.setOnClickListener{
//            if(editTextUsername.text.isEmpty()||editTextEmail.text.isEmpty()||editTextPassword.text.isEmpty()||editTextConfirmPassword.text.isEmpty()){
//                Toast.makeText(context,"Please fill in the form!!!",Toast.LENGTH_SHORT).show()
//            }else{
//                if(!editTextPassword.text.toString().equals(editTextConfirmPassword.text.toString())){
//                    Toast.makeText(context,"Your password and confirm password are not matched !!",Toast.LENGTH_SHORT).show()
//                }
//                else{
//
//                    val _username= editTextUsername.text.toString()
//                    val _email=editTextEmail.text.toString()
//                    val _password=editTextPassword.text.toString()
//                    val user = User(_username,_email,_password)
//
//                    mDatabase!!.setValue(user)
//
//                    val intent = Intent(this, GetUserDetailsActivity::class.java)
//
//                    intent.putExtra(KEY,_username)
//
//                    startActivity(intent)
//                    Toast.makeText(this, "Register successfully", Toast.LENGTH_SHORT).show()
//                    finish()
//                }
//            }
//
//        }




//    companion object{
//        const val KEY = "com.example.dietfitnessplanapp.KEY"
//
//    }

}
