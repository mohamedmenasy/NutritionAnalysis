package com.mohamednabil.nutritionanalysis.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohamednabil.nutritionanalysis.R
import com.mohamednabil.nutritionanalysis.features.analysis.view.AnalysisScreen
import com.mohamednabil.nutritionanalysis.features.analysis.viewmodel.NutritionAnalysisViewModel
import com.mohamednabil.nutritionanalysis.features.facts.view.FactsScreen
import com.mohamednabil.nutritionanalysis.features.summary.view.SummaryScreen

@Composable
fun Navigator(viewModel: NutritionAnalysisViewModel, topBarTitle: (Int) -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Analysis.route) {
        composable(route = Screen.Analysis.route) {
            AnalysisScreen(viewModel = viewModel, navController = navController)
            topBarTitle(R.string.app_name)
        }
        composable(route = Screen.Facts.route) {
            FactsScreen(viewModel = viewModel)
            topBarTitle(R.string.facts)
        }
        composable(route = Screen.Summary.route) {
            SummaryScreen(viewModel = viewModel, navController = navController)
            topBarTitle(R.string.summary)

        }
    }
}
