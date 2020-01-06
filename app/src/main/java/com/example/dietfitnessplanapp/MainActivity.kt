package com.example.dietfitnessplanapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = FirebaseDatabase.getInstance()
    private val users = db.getReference("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        /*
        Thread {
            var user = User_Entity()
            user.user_id = 1
            user.username = "ming"
            user.password = "123"

            db.appDAO().saveUser(user)

            db.appDAO().readUser().forEach {
                Log.i("@AKTDEV", "User Id is: ${it.user_id}")
                Log.i("@AKTDEV", "Username is: ${it.username}")
                Log.i("@AKTDEV", "Password is: ${it.password}")
            }


        }.start()

*/

        val context=this
        var counter = 0

        buttonSignIn.setOnClickListener() {
            counter = 0


            if (editTextEmail.text.isEmpty() || editTextPassword.text.isEmpty()) {
                Toast.makeText(context, "Please fill in the form!!!", Toast.LENGTH_SHORT).show()

            }
            else{
                if (!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text.toString()).matches()) {
                    Toast.makeText(context, "Please enter a valid email!!!", Toast.LENGTH_SHORT).show()

                }else{
                    auth.createUserWithEmailAndPassword(
                        editTextEmail.text.toString(),
                        editTextPassword.text.toString()
                    ).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            login()
                        } else {
                            Toast.makeText(context, "Please enter valid username and password!!!", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }






//                var data=db.readData()
//
//                for(i in 0..data.size-1){
//                    if(editTextUsername.text.toString().equals(data.get(i).username)){
//                        if(editTextPassword.text.toString().equals(data.get(i).password)){
//                            counter=0
//                            login()
//                        }
//                        else{
//                            Toast.makeText(context,"Invalid username or password!!",Toast.LENGTH_SHORT).show()
//                        }
//
//                    }else{
//                       counter++
//                    }
//                }
//
//                if(counter>0){
//                    Toast.makeText(context,"Invalid username or password!!",Toast.LENGTH_SHORT).show()
//                }


        }


        // get reference to textview
        val textView_clickable = findViewById(R.id.textViewGuidance) as TextView

        textView_clickable.setOnClickListener {
            // Toast.makeText(this@MainActivity, "You clicked on sign up ", Toast.LENGTH_SHORT).show()
            register();
        }
    }

    public override fun onStart(){
        super.onStart()
         val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser:FirebaseUser?){

    }



    private fun login() {

        /*
        var db=Room.databaseBuilder(applicationContext,AppDB::class.java,
            "user_db").build()



        userEntity.username = "ming"
        userEntity.password = "123"
        db.appDAO().saveUser(userEntity)



        val username = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()
*/


        val intent = Intent(this, WeightActivity::class.java)
            startActivity(intent)

        /*
        val intent= Intent(Intent.ACTION_VIEW)
        val phone:String = "tel :0123456789"

        Check package manager for app to handle an intent
       intent.setData(Uri.parse(phone))
        if(intent.resolveActivity(packageManager) !== null){

        }
        */

    }

    private fun register(){
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

}

 /*
    private fun register(){


        Explicit
        val intent = Intent(this,SecondActivity::class.java)


        val username= editTextUsername.text.toString()
        val password=editTextPassword.text.toString()
        intent.putExtra(KEY,username)
        intent.putExtra(KEY2,password)

         startActivity(intent)  // An intent without return value
        startActivityForResult(intent,REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode== REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                val reply=data?.getStringExtra(KEY3).toString()
                textViewReply.text=String.format(" %s  %s ",getString(R.string.reply),reply)
            }
        }
    }

*/




