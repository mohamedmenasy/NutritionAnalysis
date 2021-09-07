package com.mohamednabil.nutritionanalysis.features.facts.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamednabil.nutritionanalysis.R
import com.mohamednabil.nutritionanalysis.core.extension.format
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView
import com.mohamednabil.nutritionanalysis.features.analysis.viewmodel.NutritionAnalysisViewModel

@Composable
fun FactsScreen(viewModel: NutritionAnalysisViewModel) {
    val nutritionDetails: NutrientsDataView by viewModel.nutritionDetails.observeAsState(
        NutrientsDataView.empty
    )
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(
                stringResource(R.string.facts_fragment_nutrition_facts),
                style = TextStyle(fontSize = 20.sp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
        nutritionDetails.totalNutrients.forEachIndexed { index, totalNutrients ->
            item {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        "Total ${totalNutrients.label}",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        "${totalNutrients.quantity.format(1)} ${totalNutrients.unit}",
                        style = TextStyle(fontSize = 16.sp),
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (index < nutritionDetails.totalNutrients.size - 1)
                    Divider(thickness = 1.dp)
            }
        }
    }
}