package com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce


import com.google.gson.annotations.SerializedName
import com.mohamednabil.nutritionanalysis.core.extension.empty
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutrientsData

data class NutritionDetailsEntity(
    @SerializedName("calories")
    val calories: Int,
    @SerializedName("cautions")
    val cautions: List<String>,
    @SerializedName("dietLabels")
    val dietLabels: List<String>,
    @SerializedName("healthLabels")
    val healthLabels: List<String>,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>,
    @SerializedName("totalDaily")
    val totalDaily: Map<String, NutrientsDataEntity>,
    @SerializedName("totalNutrients")
    val totalNutrients: Map<String, NutrientsDataEntity>,
    @SerializedName("totalWeight")
    val totalWeight: Double,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("yield")
    val yield: Double
) {
    companion object {
        val empty = NutritionDetailsEntity(
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyMap(),
            emptyMap(),
            0.0,
            String.empty(),
            0.0
        )
    }

    fun toNutrientsData() =
        NutrientsData(
            calories,
            dietLabels,
            healthLabels,
            ingredients,
            totalWeight
        )

    private fun Map<String, NutrientsDataEntity>.toNutrientsDataInfoMap() =
        this.mapValues { it.value.toNutrientsDataInfo() }

}