package com.mohamednabil.nutritionanalysis.features.analysis.view

import android.os.Parcelable
import com.mohamednabil.nutritionanalysis.core.extension.empty
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
) : Parcelable{
    companion object{
        val empty = NutrientsDataItem(String.empty(), String.empty(), 0.0,0.0,0.0)
    }
}

@Parcelize
data class TotalNutrients(
    var label: String,
    val quantity: Double,
    val unit: String
) : Parcelable
