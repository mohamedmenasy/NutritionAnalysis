package com.mohamednabil.nutritionanalysis.features.analysis.view

import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutrientsDataInfo

data class NutrientsDataView(
    val calories: Int,
    val dietLabels: List<String>,
    val healthLabels: List<String>,
    val totalDaily: Map<String, NutrientsDataInfo>,
    val totalNutrients: Map<String, NutrientsDataInfo>,
    val totalNutrientsKCal: Map<String, NutrientsDataInfo>,
    val totalWeight: Double
)
