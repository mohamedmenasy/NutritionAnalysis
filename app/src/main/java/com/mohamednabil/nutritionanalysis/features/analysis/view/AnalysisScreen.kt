package com.mohamednabil.nutritionanalysis.features.analysis.view

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.mohamednabil.nutritionanalysis.R
import com.mohamednabil.nutritionanalysis.core.exception.Failure
import com.mohamednabil.nutritionanalysis.core.navigation.Screen
import com.mohamednabil.nutritionanalysis.features.analysis.viewmodel.NutritionAnalysisViewModel

@Composable
fun AnalysisScreen(
    viewModel: NutritionAnalysisViewModel, navController: NavController
) {

    val uiState = remember {
        viewModel.uiState
    }
    when (uiState.value) {
        AnalysisUiState.DataReady -> {
            LaunchedEffect(true) {
                navController.navigate(Screen.Summary.route)
            }
        }
        AnalysisUiState.Error -> {
            val error = viewModel.failure.observeAsState().value
            ErrorView(error, viewModel, navController)
        }
        AnalysisUiState.Loading -> {
            LoadingView()
        }
        AnalysisUiState.Normal -> {
            AnalysisView(viewModel::getNutritionDetails)
        }
    }
}

@Composable
fun AnalysisView(onAnalysisButtonClick: (ingredients: List<String>) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }
    var isAnalyzeButtonEnabled by rememberSaveable { mutableStateOf(false) }
    val padding = 16.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(
                text = stringResource(R.string.main_activity_header),
                style = TextStyle(fontSize = 18.sp),
                textAlign = TextAlign.Center
            )
            TextField(
                modifier = Modifier
                    .weight(1f)
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
                    onAnalysisButtonClick(ingredientsList)
                },
                enabled = isAnalyzeButtonEnabled,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
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

@Composable
fun LoadingView() {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun ErrorView(
    failure: Failure?,
    viewModel: NutritionAnalysisViewModel,
    navController: NavController
) {
    when (failure) {
        is Failure.NetworkConnection -> RenderErrorView(
            R.string.failure_network_connection, viewModel,
            navController
        )
        is Failure.ServerError -> RenderErrorView(
            R.string.failure_server_error,
            viewModel,
            navController
        )
        is Failure.FeatureFailure -> RenderErrorView(
            R.string.failure_no_data_for_ingredients, viewModel,
            navController
        )
        else -> RenderErrorView(R.string.failure_server_error, viewModel, navController)
    }
}

@Composable
private fun RenderErrorView(
    @StringRes messageResID: Int,
    viewModel: NutritionAnalysisViewModel,
    navController: NavController
) {
    var showPopup by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = {
            showPopup = false
        },
        text = {
            Text(
                stringResource(messageResID),
                style = TextStyle(fontSize = 16.sp)
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    navController.navigate(Screen.Analysis.route) {
                        popUpTo(Screen.Analysis.route) { inclusive = true }
                    }
                    viewModel.resetUiStatus()
                    showPopup = false
                }
            ) {
                Text(text = stringResource(R.string.ok))
            }
        })
}