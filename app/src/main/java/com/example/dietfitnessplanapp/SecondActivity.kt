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


class SecondActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "SecondActivity"
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        buttonRegister.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()

    }


    override fun onClick(view: View?) {
        val i = view!!.id

        when (i) {
            R.id.buttonRegister -> register(editTextUsername.text.toString(),editTextEmail.text.toString(),editTextPassword.text.toString(),editTextConfirmPassword.text.toString())
        }
    }

    private fun register(username: String, email: String, password: String, confirmPassword: String) {


        if (!validateForm(username, email, password, confirmPassword)) {
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this, GetUserDetailsActivity::class.java)
                    val user = mAuth!!.currentUser

                    intent.putExtra(KEY1,editTextUsername.text.toString())
                    intent.putExtra(KEY2,editTextEmail.text.toString())
                    intent.putExtra(KEY3,user!!.uid)

                    startActivity(intent)
                    finish()

                } else {

                    Toast.makeText(applicationContext, "Email had taken by other user!!", Toast.LENGTH_SHORT).show()
                }
            }


    }

    private fun validateForm(username :String ,email: String, password: String, confirmPassword: String): Boolean {

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(applicationContext, "Please enter your username!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter your email address!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter your password!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(applicationContext, "Please enter your confirm password!", Toast.LENGTH_SHORT).show()
            return false
        }

        if(!password.equals(confirmPassword)){
            Toast.makeText(applicationContext, "Your password and confirm password not matched!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(applicationContext, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }



    companion object{
        const val KEY1 = "com.example.dietfitnessplanapp.KEY1"
        const val KEY2 = "com.example.dietfitnessplanapp.KEY2"
        const val KEY3 = "com.example.dietfitnessplanapp.KEY3"

    }

}
