package com.mohamednabil.core.core.extension

fun Double.format(digits: Int) = "%.${digits}f".format(this)
