package com.mohamednabil.nutritionanalysis.features.analysis.usecase

import com.mohamednabil.core.core.exception.Failure
import com.mohamednabil.core.core.functional.Either
import com.mohamednabil.core.core.functional.getOrElse
import com.mohamednabil.core.core.functional.map
import com.mohamednabil.core.core.interactor.UseCase
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutrientsData
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutritionRepository
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.request.Ingredients
import com.mohamednabil.nutritionanalysis.features.analysis.view.NutrientsDataView
import javax.inject.Inject

class GetNutrition @Inject constructor(private val moviesRepository: NutritionRepository) :
    UseCase<NutrientsDataView, GetNutrition.Params>() {

    data class Params(val ingredients: Ingredients)

    override suspend fun run(params: Params): Either<Failure, NutrientsDataView> {
        val result = moviesRepository.getNutritionsForIngredients(params.ingredients)
        val receivedNutrientsData = result.getOrElse(NutrientsData.empty)
        if (receivedNutrientsData.totalNutrients.isNullOrEmpty()
            || receivedNutrientsData.calories == 0
            || receivedNutrientsData.dietLabels.isNullOrEmpty()
            || receivedNutrientsData.healthLabels.isNullOrEmpty()
        ) {
            return Either.Left<Failure>(Failure.FeatureFailure)
        }

        return result.map {
            it.toNutrientsDataView()
        }
    }

}