package com.mohamednabil.nutritionanalysis.features.analysis.data.remote

import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView

data class NutrientsData(
    val calories: Int,
    val dietLabels: List<String>,
    val healthLabels: List<String>,
    val totalDaily: Map<String, NutrientsDataInfo>,
    val totalNutrients: Map<String, NutrientsDataInfo>,
    val totalNutrientsKCal: Map<String, NutrientsDataInfo>,
    val totalWeight: Double
) {
    companion object {
        val empty = NutrientsData(
            0,
            emptyList(),
            emptyList(),
            emptyMap(),
            emptyMap(),
            emptyMap(),
            0.0
        )
    }

    fun toNutrientsDataView() = NutrientsDataView(
        calories,
        dietLabels,
        healthLabels,
        totalDaily,
        totalNutrients,
        totalNutrientsKCal,
        totalWeight
    )
}

