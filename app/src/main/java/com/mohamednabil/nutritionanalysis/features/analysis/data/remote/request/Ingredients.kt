package com.mohamednabil.nutritionanalysis.features.analysis.data.remote.request

import com.google.gson.annotations.SerializedName

data class Ingredients(
    @SerializedName("ingr")
    val ingredientsList: List<String>
)
