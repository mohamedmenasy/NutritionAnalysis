package com.mohamednabil.nutritionanalysis.features.summary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mohamednabil.nutritionanalysis.core.extension.format
import com.mohamednabil.nutritionanalysis.core.ui.theme.NutritionAnalysisTheme
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataItem
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView
import com.mohamednabil.nutritionanalysis.features.analysis.viewmodel.NutritionAnalysisViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummaryFragment : Fragment() {
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

                            SummaryScreen()
                        })
                }
            }
        }
    }

    @Composable
    fun SummaryScreen() {
        val nutritionDetails: NutrientsDataView by viewModel.nutritionDetails.observeAsState(
            NutrientsDataView.empty
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            nutritionDetails.data.forEach {
                item {
                    RenderNutrientItem(it)
                }
            }
            item {
                //TODO : move this to the bottom of the screen
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        //TODO: remove arguments
                        val action =
                            SummaryFragmentDirections.actionSummaryFragmentToFactsFragment(
                                nutritionDetails
                            )
                        this@SummaryFragment.findNavController().navigate(
                            action
                        )

                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        "Total nutrition : ${nutritionDetails.totalNutrients[0].quantity.format(1)} ${nutritionDetails.totalNutrients[0].unit}",
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
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    nutrientsDataItem.food.uppercase(), style = TextStyle(fontSize = 18.sp),
                    fontWeight = FontWeight.Bold,
                )
                Text("Quantity: ${nutrientsDataItem.quantity.format(1)} ${nutrientsDataItem.measure}")
                Text("Calories: ${nutrientsDataItem.calories.format(1)} kcal")
                Text("Weight: ${nutrientsDataItem.weight.format(1)} g")

            }
        }
    }
}




