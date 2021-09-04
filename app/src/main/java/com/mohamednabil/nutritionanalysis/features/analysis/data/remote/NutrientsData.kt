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

