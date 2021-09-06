package com.mohamednabil.nutritionanalysis.features.analysis.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mohamednabil.nutritionanalysis.core.platform.BaseViewModel
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.request.Ingredients
import com.mohamednabil.nutritionanalysis.features.analysis.usecase.GetNutrition
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NutritionAnalysisViewModel @Inject constructor(
    private val getNutrition: GetNutrition
) : BaseViewModel() {

    private val _nutritionDetails: MutableLiveData<NutrientsDataView> = MutableLiveData()
    val nutritionDetails: LiveData<NutrientsDataView> = _nutritionDetails

    fun getNutritionDetails(ingredients: List<String>) {

        return getNutrition(GetNutrition.Params(Ingredients(ingredients)), viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleNutritionDetails
            )
        }
    }


    private fun handleNutritionDetails(nutrientsData: NutrientsDataView) {
        _nutritionDetails.value = nutrientsData
    }
}