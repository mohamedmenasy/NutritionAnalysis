package com.mohamednabil.nutritionanalysis.features.analysis.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamednabil.core.core.exception.Failure
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.request.Ingredients
import com.mohamednabil.nutritionanalysis.features.analysis.usecase.GetNutrition
import com.mohamednabil.nutritionanalysis.features.analysis.view.AnalysisUiState
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NutritionAnalysisViewModel @Inject constructor(
    private val getNutrition: GetNutrition
) : ViewModel() {

    private val _nutritionDetails: MutableLiveData<NutrientsDataView> = MutableLiveData()
    val nutritionDetails: LiveData<NutrientsDataView> = _nutritionDetails
    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure
    private val _uiState = mutableStateOf<AnalysisUiState>(AnalysisUiState.Normal)
    val uiState: State<AnalysisUiState>
        get() = _uiState

    fun getNutritionDetails(ingredients: List<String>) {
        _uiState.value = AnalysisUiState.Loading
        return getNutrition(GetNutrition.Params(Ingredients(ingredients)), viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleNutritionDetails
            )
        }
    }

    private fun handleFailure(failure: Failure) {
        _failure.value = failure
        _uiState.value = AnalysisUiState.Error
    }


    private fun handleNutritionDetails(nutrientsData: NutrientsDataView) {
        _nutritionDetails.value = nutrientsData
        _uiState.value = AnalysisUiState.DataReady
    }

    fun resetUiStatus() {
        _uiState.value = AnalysisUiState.Normal

    }
}