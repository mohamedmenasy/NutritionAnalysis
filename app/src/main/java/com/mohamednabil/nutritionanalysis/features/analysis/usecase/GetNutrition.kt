package com.mohamednabil.nutritionanalysis.features.analysis.usecase

import com.mohamednabil.nutritionanalysis.core.interactor.UseCase
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutrientsData
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutritionRepository
import javax.inject.Inject

class GetNutrition @Inject constructor(private val moviesRepository: NutritionRepository) :
    UseCase<NutrientsData, GetNutrition.Params>() {

    data class Params(val ingredients: String)

    override suspend fun run(params: Params) =
        moviesRepository.getNutritionsForIngredients(params.ingredients)

}