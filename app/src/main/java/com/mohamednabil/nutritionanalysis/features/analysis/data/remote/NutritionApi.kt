package com.mohamednabil.nutritionanalysis.features.analysis.data.remote

import com.mohamednabil.nutritionanalysis.BuildConfig
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce.NutritionDetailsEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NutritionApi {
    companion object {
        private const val APP_ID = BuildConfig.APP_ID
        private const val APP_KEY = BuildConfig.APP_KEY
        private const val NUTRITION_TYPE = "logging"
        private const val GET_NUTRITION =
            "/api/nutrition-data?app_id=$APP_ID&app_key=$APP_KEY&nutrition-type=$NUTRITION_TYPE"

    }

    @GET(GET_NUTRITION)
    fun getNutrition(
        @Query("ingr") ingredients: String
    ): Call<NutritionDetailsEntity>
}