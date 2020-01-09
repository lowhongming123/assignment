package com.example.dietfitnessplanapp

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.Exclude

@IgnoreExtraProperties
class User{
    var username : String? = null
    var email : String? = null


    constructor(){

    }

    constructor(username: String?, email: String?) {
        this.username = username
        this.email = email
    }

    @Exclude
    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("username", username!!)
        result.put("email", email!!)

        return result
    }
}

//    var userTarget : String,
//    var expectedWeight : Int,
//    var gender : String,
//    var currentWeight : Int,
//    var currentHeight : Int,
//    var country : String

//{
//    fun setInformation(username: String,email: String,password: String,userTarget: String,expectedWeight: Int,gender: String,currentWeight: Int,currentHeight: Int,country: String){
//        this.username= username
//        this.email = email
//        this.password = password
//        this.userTarget = userTarget
//        this.expectedWeight = expectedWeight
//        this.gender = gender
//        this.currentWeight = currentWeight
//        this.currentHeight = currentHeight
//        this.country = country
//    }
//}








