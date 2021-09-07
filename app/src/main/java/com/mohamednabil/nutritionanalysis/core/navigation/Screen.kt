package com.mohamednabil.nutritionanalysis.core.navigation

sealed class Screen(open val route: String = "") {
    object Analysis : Screen("Analysis")
    object Facts : Screen("Facts")
    object Summary : Screen("Summary")
}
