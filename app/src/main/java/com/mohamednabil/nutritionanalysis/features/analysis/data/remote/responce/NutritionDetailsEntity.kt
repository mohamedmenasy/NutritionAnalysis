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
    @SerializedName("totalDaily")
    val totalDaily: Map<String, NutrientsDataEntity>,
    @SerializedName("totalNutrients")
    val totalNutrients: Map<String, NutrientsDataEntity>,
    @SerializedName("totalNutrientsKCal")
    val totalNutrientsKCal: Map<String, NutrientsDataEntity>,
    @SerializedName("totalWeight")
    val totalWeight: Double,
    @SerializedName("uri")
    val uri: String
) {
    companion object {
        val empty = NutritionDetailsEntity(
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyMap(),
            emptyMap(),
            emptyMap(),
            0.0,
            String.empty()
        )
    }

    fun toNutrientsData() =
        NutrientsData(
            calories,
            dietLabels,
            healthLabels,
            totalDaily.toNutrientsDataInfoMap(),
            totalNutrients.toNutrientsDataInfoMap(),
            totalNutrientsKCal.toNutrientsDataInfoMap(),
            totalWeight
        )

    private fun Map<String, NutrientsDataEntity>.toNutrientsDataInfoMap() =
        this.mapValues { it.value.toNutrientsDataInfo() }

}