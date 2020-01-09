package com.example.dietfitnessplanapp

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.Exclude

@IgnoreExtraProperties
class User{
    var username : String? = null
    var email : String? = null
    var userTarget : String? = null
    var expectedWeight : Int? = 0
    var gender : String? = null
    var currentWeight : Int? = 0
    var currentHeight : Int? = 0
    var country : String? = null


    constructor(){

    }

    constructor(username: String,email: String,userTarget: String,expectedWeight: Int,gender: String,currentWeight: Int,currentHeight: Int,country: String){
        this.username= username
        this.email = email
        this.userTarget = userTarget
        this.expectedWeight = expectedWeight
        this.gender = gender
        this.currentWeight = currentWeight
        this.currentHeight = currentHeight
        this.country = country
    }

    @Exclude
    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("username", username!!)
        result.put("email", email!!)
        result.put("usertarget", userTarget!!)
        result.put("expectedweight", expectedWeight!!)
        result.put("gender", gender!!)
        result.put("currentweight", currentWeight!!)
        result.put("currentheight", currentHeight!!)
        result.put("country", country!!)

        return result
    }
}









