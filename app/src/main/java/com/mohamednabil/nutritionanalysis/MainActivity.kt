package com.mohamednabil.nutritionanalysis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamednabil.nutritionanalysis.core.navigation.Navigator
import com.mohamednabil.nutritionanalysis.core.ui.theme.NutritionAnalysisTheme
import com.mohamednabil.nutritionanalysis.features.analysis.viewmodel.NutritionAnalysisViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: NutritionAnalysisViewModel = viewModel()
            var topAppBarTitle by rememberSaveable { mutableStateOf(getString(R.string.app_name)) }

            NutritionAnalysisTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(topAppBarTitle) },
                        )
                    },
                ) {
                    Navigator(viewModel, topBarTitle = { topAppBarTitle = getString(it) })
                }
            }
        }
    }
}