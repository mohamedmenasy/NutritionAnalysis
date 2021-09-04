package com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce

import com.google.gson.annotations.SerializedName
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutrientsDataInfo

data class NutrientsDataEntity(
    @SerializedName("label")
    val label: String,
    @SerializedName("quantity")
    val quantity: Double,
    @SerializedName("unit")
    val unit: String
) {

    fun toNutrientsDataInfo() = NutrientsDataInfo(label, quantity, unit)
}


