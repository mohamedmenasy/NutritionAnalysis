package com.mohamednabil.nutritionanalysis.core.extension

fun Double.format(digits: Int) = "%.${digits}f".format(this)
