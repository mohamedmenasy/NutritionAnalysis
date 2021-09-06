package com.mohamednabil.nutritionanalysis.features.analysis.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutrientsDataInfo(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable
