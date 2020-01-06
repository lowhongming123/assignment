package com.example.dietfitnessplanapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private val db = FirebaseDatabase.getInstance()
    private val users = db.getReference("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val context=this
//        var db=DataBaseHandler(context)

        buttonRegister.setOnClickListener{
            if(editTextUsername.text.isEmpty()||editTextUsername.text.isEmpty()||editTextPassword.text.isEmpty()||editTextConfirmPassword.text.isEmpty()){
                Toast.makeText(context,"Please fill in the form!!!",Toast.LENGTH_SHORT).show()
            }else{
                if(!editTextPassword.text.toString().equals(editTextConfirmPassword.text.toString())){
                    Toast.makeText(context,"Your password and confirm password are not matched !!",Toast.LENGTH_SHORT).show()
                }
                else{
                    val _username= editTextUsername.text.toString()
                    val _email=editTextEmail.text.toString()
                    val _password=editTextPassword.text.toString()
//                    var user=User(editTextUsername.text.toString(),editTextEmail.text.toString(),editTextPassword.text.toString())
//                    db.insertDate(user)



                    val intent = Intent(this, GetUserDetailsActivity::class.java)

                    val user = User(username = _username, email = _email, password = _password)

                    //Using phone as a key
                    users.child(_username).setValue(user)

                    Toast.makeText(context,"Register successfully !!",Toast.LENGTH_SHORT).show()

                    intent.putExtra(KEY,_username)
                    startActivity(intent)  // An intent without return value
                    finish()
                }
            }

        }




        //testing()
        //val message = intent.getStringExtra(MainActivity.KEY)
        //val number=intent.getIntExtra(MainActivity.KEY2,0)
        //textViewMessage.text= String.format("%s  %s ",getString(R.string.message),message)
       // textViewLuckyNumber.text=String.format("%s  %s ",getString(R.string.yourLuckyNumber),number)




       // buttonDone.setOnClickListener(){

          //  if(!editTextReply.text.isEmpty()){

                //val reply=editTextReply.text.toString()
                //val intent=getIntent()   //return the MainActivity intent
                //intent.putExtra(MainActivity.KEY3,reply)

                //inform the MainActivity that everything is ok
                //setResult(Activity.RESULT_OK,intent)
            //}else{
                //setResult(Activity.RESULT_CANCELED)
            //}

           // finish()

       // }
    }

    /*
    private fun register(){

        val username= editTextUsername.text.toString()
        val email=editTextEmail.text.toString()
        val password=editTextPassword.text.toString()
        val confirmPassword=editTextConfirmPassword.text.toString()

        val ref = FirebaseDatabase.getInstance().getReference("users")
        val id = ref.push().key

        val user = User(id.toString(),username,email,password,confirmPassword)
        // store the user object into the unique Id which is userId
        // addOnCompleteListener chk whether the data had saved inside database
        ref.child(id.toString()).setValue(user).addOnCompleteListener {
            Toast.makeText(applicationContext,"User record has been saved successfully.",Toast.LENGTH_LONG).show()

        }

        finish()

    }
    */

    companion object{
        const val KEY = "com.example.dietfitnessplanapp.KEY"

    }

}
