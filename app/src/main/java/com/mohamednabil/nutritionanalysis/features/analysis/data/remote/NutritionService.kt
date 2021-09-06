package com.mohamednabil.nutritionanalysis.features.analysis.data.remote

import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.request.Ingredients
import retrofit2.Retrofit
import javax.inject.Inject

class NutritionService @Inject constructor(retrofit: Retrofit) : NutritionApi {
    private val nutritionApi by lazy { retrofit.create(NutritionApi::class.java) }

    override fun getNutrition(
        ingredients: Ingredients
    ) = nutritionApi.getNutrition(ingredients = ingredients)
}