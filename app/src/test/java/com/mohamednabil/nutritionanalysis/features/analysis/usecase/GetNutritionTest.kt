package com.mohamednabil.nutritionanalysis.features.analysis.usecase

import com.mohamednabil.core.core.UnitTest
import com.mohamednabil.core.core.functional.Either
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutrientsData
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutritionRepository
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.request.Ingredients
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNutritionTest : UnitTest() {
    private lateinit var getNutrition: GetNutrition

    @MockK
    private lateinit var nutritionRepository: NutritionRepository

    @Before
    fun setUp() {
        getNutrition = GetNutrition(nutritionRepository)
        every { nutritionRepository.getNutritionsForIngredients(Ingredients(listOf("1 cup rice"))) } returns Either.Right(
            NutrientsData.empty
        )
    }

    @Test
    fun `given NutritionRepository when call GetNutrition usecase then get data from repository`() {
        runBlocking { getNutrition.run(GetNutrition.Params(Ingredients(listOf("1 cup rice")))) }

        verify(exactly = 1) { nutritionRepository.getNutritionsForIngredients(Ingredients(listOf("1 cup rice"))) }
    }
}