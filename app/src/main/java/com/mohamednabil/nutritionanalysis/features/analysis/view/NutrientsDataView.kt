package com.mohamednabil.nutritionanalysis.features.analysis.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutrientsDataView(
    val data: ArrayList<NutrientsDataItem>
) : Parcelable {
    companion object {
        val empty = NutrientsDataView(
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
