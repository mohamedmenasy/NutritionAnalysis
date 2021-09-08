package com.mohamednabil.nutritionanalysis.features.common.navigation

sealed class Screen(open val route: String = "") {
    object Analysis : Screen("Analysis")
    object Facts : Screen("Facts")
    object Summary : Screen("Summary")
}
