package com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce


import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("parsed")
    val parsed: List<Parsed>,
    @SerializedName("text")
    val text: String
)