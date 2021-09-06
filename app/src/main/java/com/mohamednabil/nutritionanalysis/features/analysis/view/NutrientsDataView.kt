package com.mohamednabil.nutritionanalysis.features.analysis.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutrientsDataView(
    val data: ArrayList<NutrientsDataItem>,
    val totalNutrients: MutableList<TotalNutrients>
) : Parcelable {
    companion object {
        val empty = NutrientsDataView(
            arrayListOf(),
            arrayListOf()
        )
    }
}

@Parcelize
data class NutrientsDataItem(
    val food: String,
    val measure: String,
    val calories: Double,
    val quantity: Double,
    val weight: Double
) : Parcelable

@Parcelize
data class TotalNutrients(
    var label: String,
    val quantity: Double,
    val unit: String
) : Parcelable
