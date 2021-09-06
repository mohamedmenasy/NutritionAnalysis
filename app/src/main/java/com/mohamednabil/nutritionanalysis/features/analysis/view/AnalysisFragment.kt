package com.mohamednabil.nutritionanalysis.features.analysis.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mohamednabil.nutritionanalysis.R
import com.mohamednabil.nutritionanalysis.core.exception.Failure
import com.mohamednabil.nutritionanalysis.core.extension.failure
import com.mohamednabil.nutritionanalysis.core.extension.observe
import com.mohamednabil.nutritionanalysis.core.ui.theme.NutritionAnalysisTheme
import com.mohamednabil.nutritionanalysis.features.analysis.viewmodel.NutritionAnalysisViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalysisFragment : Fragment() {

    private val viewModel by viewModels<NutritionAnalysisViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            observe(nutritionDetails, ::showSummaryScreen)
            failure(failure, ::handleFailure)
        }
    }

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
                            AnalysisScreen(viewModel)
                        })
                }
            }
        }
    }

    private fun showSummaryScreen(nutrientsDataView: NutrientsDataView?) {
        val action = nutrientsDataView?.let {
            AnalysisFragmentDirections.actionAnalysisFragmentToSummaryFragment(
                it
            )
        }
        action?.let { this.findNavController().navigate(it) }

    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            else -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes messageResID: Int) {
        Toast.makeText(context, messageResID, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun AnalysisScreen(viewModel: NutritionAnalysisViewModel) {
    var searchText by remember { mutableStateOf("") }
    var isAnalyzeButtonEnabled by remember { mutableStateOf(false) }

    val padding = 16.dp
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
                    isAnalyzeButtonEnabled = it.trim().isNotEmpty()
                },
                placeholder = {
                    Text(
                        "Example: \n1 cup rice,\n" +
                                "10 oz chickpeas", style = TextStyle(fontSize = 18.sp)
                    )
                }
            )
            Button(
                onClick = {
                    val ingredientsList =
                        searchText.replace("\r", "").trim().split("\n").toList()
                    viewModel.getNutritionDetails(ingredientsList)
                },
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