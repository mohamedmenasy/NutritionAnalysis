package com.mohamednabil.nutritionanalysis.features.analysis.data.remote

import com.mohamednabil.core.core.exception.Failure
import com.mohamednabil.core.core.functional.Either
import com.mohamednabil.core.core.functional.Either.Left
import com.mohamednabil.core.core.functional.Either.Right
import com.mohamednabil.core.core.platform.NetworkHandler
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.request.Ingredients
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce.NutritionDetailsEntity
import retrofit2.Call
import javax.inject.Inject

interface NutritionRepository {
    fun getNutritionsForIngredients(ingredients: Ingredients): Either<Failure, NutrientsData>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: NutritionService
    ) : NutritionRepository {
        override fun getNutritionsForIngredients(ingredients: Ingredients): Either<Failure, NutrientsData> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    service.getNutrition(ingredients),
                    { it.toNutrientsData() },
                    NutritionDetailsEntity.empty
                )
                false -> Left(Failure.NetworkConnection)
            }
        }

        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Left(Failure.ServerError)
            }
        }
    }

}