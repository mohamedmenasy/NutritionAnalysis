package com.mohamednabil.nutritionanalysis.features.summary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mohamednabil.nutritionanalysis.core.extension.format
import com.mohamednabil.nutritionanalysis.core.ui.theme.NutritionAnalysisTheme
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataItem
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryFragment : Fragment() {
    private val args: SummaryFragmentArgs by navArgs()

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
                            val nutrientsData = arguments?.get("NutrientsData")
                            SummaryScreen(args.nutrientsData)
                        })
                }
            }
        }
    }
}

@Composable
fun SummaryScreen(nutrientsData: NutrientsDataView) {

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {

        nutrientsData.data.forEach {
            item {
                RenderNutrientItem(it)
            }
        }
    }
}

@Composable
fun RenderNutrientItem(nutrientsDataItem: NutrientsDataItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text("Food: ${nutrientsDataItem.food}")
            Text("Quantity: ${nutrientsDataItem.quantity.format(1)} ${nutrientsDataItem.measure}")
            Text("Calories: ${nutrientsDataItem.calories.format(1)} kcal")
            Text("Weight: ${nutrientsDataItem.weight.format(1)} g")
        }
    }
}

