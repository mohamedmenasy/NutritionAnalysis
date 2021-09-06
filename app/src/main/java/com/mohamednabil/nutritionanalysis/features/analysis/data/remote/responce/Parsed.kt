package com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce


import com.google.gson.annotations.SerializedName

data class Parsed(
    @SerializedName("food")
    val food: String,
    @SerializedName("foodId")
    val foodId: String,
    @SerializedName("foodMatch")
    val foodMatch: String,
    @SerializedName("measure")
    val measure: String,
    @SerializedName("measureURI")
    val measureURI: String,
    @SerializedName("nutrients")
    val nutrients: Map<String, NutrientsDataEntity>,
    @SerializedName("quantity")
    val quantity: Double,
    @SerializedName("retainedWeight")
    val retainedWeight: Double,
    @SerializedName("status")
    val status: String,
    @SerializedName("weight")
    val weight: Double
)