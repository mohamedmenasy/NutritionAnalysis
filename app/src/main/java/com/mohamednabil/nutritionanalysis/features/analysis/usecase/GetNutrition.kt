package com.mohamednabil.nutritionanalysis.features.analysis.usecase

import com.mohamednabil.nutritionanalysis.core.functional.map
import com.mohamednabil.nutritionanalysis.core.interactor.UseCase
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutritionRepository
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.request.Ingredients
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView
import javax.inject.Inject

class GetNutrition @Inject constructor(private val moviesRepository: NutritionRepository) :
    UseCase<NutrientsDataView, GetNutrition.Params>() {

    data class Params(val ingredients: Ingredients)

    override suspend fun run(params: Params) =
        moviesRepository.getNutritionsForIngredients(params.ingredients)
            .map {
                it.toNutrientsDataView()
            }
}