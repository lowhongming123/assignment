package com.example.dietfitnessplanapp

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Food{
    var foodName:String? = null
    var foodCalories:Long? = null
    var foodImage:String? = null
    var foodDescription:String? = null
    var foodCaloriesType: String? = null


    constructor(){}

    constructor(
        foodName: String?,
        foodCalories: Long?,
        foodImage: String?,
        foodDescription: String?,
        foodCaloriesType: String?
    ) {
        this.foodName = foodName
        this.foodCalories = foodCalories
        this.foodImage = foodImage
        this.foodDescription = foodDescription
        this.foodCaloriesType = foodCaloriesType
    }

    constructor(foodName: String?, foodCalories: Long?, foodDescription: String?,foodCaloriesType: String?) {
        this.foodName = foodName
        this.foodCalories = foodCalories
        this.foodDescription = foodDescription
        this.foodCaloriesType = foodCaloriesType
    }

    @Exclude
    fun toMap():Map<String,Any>{
        val result = HashMap<String, Any>()
        result.put("foodName",foodName!!)
        result.put("foodCalories",foodCalories!!)
        result.put("foodImage",foodImage!!)
        result.put("foodDescription",foodDescription!!)
        result.put("foodCaloriesType",foodCaloriesType!!)

        return result
    }
}