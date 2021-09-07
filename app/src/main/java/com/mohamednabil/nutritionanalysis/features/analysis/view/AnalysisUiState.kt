package com.mohamednabil.nutritionanalysis.features.analysis.view

sealed class AnalysisUiState {
    object Loading : AnalysisUiState()
    object Normal : AnalysisUiState()
    object DataReady : AnalysisUiState()
    object Error : AnalysisUiState()
}