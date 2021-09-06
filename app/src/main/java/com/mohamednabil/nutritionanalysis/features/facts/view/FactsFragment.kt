package com.mohamednabil.nutritionanalysis.features.facts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mohamednabil.nutritionanalysis.core.extension.format
import com.mohamednabil.nutritionanalysis.core.ui.theme.NutritionAnalysisTheme
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView
import com.mohamednabil.nutritionanalysis.features.analysis.viewmodel.NutritionAnalysisViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactsFragment : Fragment() {
    private val viewModel by activityViewModels<NutritionAnalysisViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                NutritionAnalysisTheme {
                    Scaffold(
                        content = {
                            FactsScreen()
                        })
                }
            }
        }
    }

    @Composable
    fun FactsScreen() {
        val nutritionDetails: NutrientsDataView by viewModel.nutritionDetails.observeAsState(
            NutrientsDataView.empty
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nutrition Facts")
            nutritionDetails.totalNutrients.forEach {
                Text("Total ${it.label}: ${it.quantity.format(1)} ${it.unit}")
            }
        }
    }
}

