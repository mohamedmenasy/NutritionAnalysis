package com.mohamednabil.nutritionanalysis.features.analysis.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamednabil.nutritionanalysis.R
import com.mohamednabil.nutritionanalysis.core.exception.Failure
import com.mohamednabil.nutritionanalysis.core.extension.failure
import com.mohamednabil.nutritionanalysis.core.extension.observe
import com.mohamednabil.nutritionanalysis.core.ui.theme.NutritionAnalysisTheme
import com.mohamednabil.nutritionanalysis.features.analysis.viewmodel.NutritionAnalysisViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalysisActivity : ComponentActivity() {
    private val viewModel by viewModels<NutritionAnalysisViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            observe(nutritionDetails, ::renderNutritionDetails)
            failure(failure, ::handleFailure)
        }
        setContent {
            NutritionAnalysisTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RenderScreen()
                }
            }
        }
    }


    private fun renderNutritionDetails(nutrientsDataView: NutrientsDataView?) {
        //TODO : navigate to the breakdown activity
        Log.d("data", nutrientsDataView.toString())
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            else -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes messageResID: Int) {
        Toast.makeText(this, messageResID, Toast.LENGTH_LONG).show()
    }

    @Composable
    fun RenderScreen() {
        var searchText by remember { mutableStateOf("") }
        var isAnalyzeButtonEnabled by remember { mutableStateOf(false) }

        val padding = 16.dp
        Column {
            TopAppBar(title = { Text(text = stringResource(R.string.main_activity_nutrition_analyzer)) })
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {

                    Text(
                        text = stringResource(R.string.main_activity_header),
                        style = TextStyle(fontSize = 18.sp)
                    )
                    TextField(
                        modifier = Modifier
                            .weight(4f)
                            .padding(top = padding)
                            .fillMaxWidth(),
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            isAnalyzeButtonEnabled = it.isNotEmpty()
                        },
                        placeholder = {
                            Text(
                                "Example: \n1 cup rice,\n" +
                                        "10 oz chickpeas", style = TextStyle(fontSize = 18.sp)
                            )
                        }
                    )
                    Button(
                        onClick = { viewModel.getNutritionDetails(searchText) },
                        enabled = isAnalyzeButtonEnabled,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = padding)
                            .fillMaxWidth()
                    ) {
                        Text(
                            stringResource(R.string.main_activity_analyze),
                            style = TextStyle(fontSize = 20.sp)
                        )

                    }
                }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        NutritionAnalysisTheme {
            RenderScreen()
        }
    }
}