package com.mohamednabil.nutritionanalysis.features.summary.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mohamednabil.nutritionanalysis.core.extension.format
import com.mohamednabil.nutritionanalysis.core.navigation.Screen
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataItem
import com.mohamednabil.nutritionanalysis.features.analysis.viewmodel.NutritionAnalysisViewModel

@Composable
fun SummaryScreen(viewModel: NutritionAnalysisViewModel, navController: NavController) {
    val nutritionDetails = viewModel.nutritionDetails.observeAsState().value
    viewModel.resetUiStatus()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        nutritionDetails?.data?.forEach {
            item {
                RenderNutrientItem(it)
            }
        }
        item {
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.Facts.route)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    "Total nutrition : ${
                        nutritionDetails?.totalNutrients?.get(0)?.quantity?.format(
                            1
                        )
                    } ${
                        nutritionDetails?.totalNutrients?.get(
                            0
                        )?.unit
                    }",
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

