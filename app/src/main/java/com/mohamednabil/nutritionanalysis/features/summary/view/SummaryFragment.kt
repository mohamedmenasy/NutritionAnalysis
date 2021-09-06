package com.mohamednabil.nutritionanalysis.features.summary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var totalNutritions = 0.0

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        nutrientsData.data.forEach {
            item {
                RenderNutrientItem(it)
                totalNutritions += it.calories
            }
        }
        item {
            //TODO : move this to the bottom of the screen
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = {
                //TODO: go to facts fragment
            }, modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Total nutrition : ${totalNutritions.format(1)} kcal",
                    style = TextStyle(fontSize = 20.sp)
                )
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

@Preview
@Composable
fun Preview() {
    SummaryScreen(NutrientsDataView.empty)
}

