package com.mohamednabil.nutritionanalysis.features.analysis.data.remote

import com.mohamednabil.nutritionanalysis.BuildConfig
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.request.Ingredients
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce.NutritionDetailsEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

internal interface NutritionApi {
    companion object {
        private const val APP_ID = BuildConfig.APP_ID
        private const val APP_KEY = BuildConfig.APP_KEY
        private const val GET_NUTRITION =
            "/api/nutrition-details?app_id=$APP_ID&app_key=$APP_KEY"

    }

    @POST(GET_NUTRITION)
    fun getNutrition(
        @Body ingredients: Ingredients
    ): Call<NutritionDetailsEntity>
}