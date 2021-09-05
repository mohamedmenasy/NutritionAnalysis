package com.mohamednabil.nutritionanalysis.features.analysis.viewmodel

import com.mohamednabil.nutritionanalysis.core.AndroidTest
import com.mohamednabil.nutritionanalysis.core.functional.Either.Right
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutrientsData
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutrientsDataInfo
import com.mohamednabil.nutritionanalysis.features.analysis.usecase.GetNutrition
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test

class NutritionAnalysisViewModelTest : AndroidTest() {
    private lateinit var nutritionAnalysisViewModel: NutritionAnalysisViewModel

    @MockK
    private lateinit var getNutrition: GetNutrition

    @Before
    fun setUp() {
        nutritionAnalysisViewModel = NutritionAnalysisViewModel(getNutrition)
    }

    @Test
    fun `given GetNutrition usecase when call getNutritionDetails then update live data`() {
        val nutrientsData = NutrientsData(
            1000,
            listOf("LOW_FAT", "LOW_SODIUM"),
            listOf(
                "FAT_FREE",
                "LOW_FAT_ABS",
                "SUGAR_CONSCIOUS",
                "LOW_POTASSIUM",
                "KIDNEY_FRIENDLY",
            ),
            mapOf(
                "ENERC_KCAL" to NutrientsDataInfo("Energy", 13.0, "%"),
                "FAT" to NutrientsDataInfo("FAT", 0.8615384615384617, "%")
            ),
            mapOf(
                "ENERC_KCAL" to NutrientsDataInfo("Energy", 13.0, "%"),
                "FAT" to NutrientsDataInfo("FAT", 0.8615384615384617, "%")
            ),
            mapOf(
                "ENERC_KCAL" to NutrientsDataInfo("Energy", 13.0, "%"),
                "FAT" to NutrientsDataInfo("FAT", 0.8615384615384617, "%")
            ),
            200.0
        )
        coEvery { getNutrition.run(any()) } returns Right(nutrientsData)

        nutritionAnalysisViewModel.nutritionDetails.observeForever {
            with(it!!) {
                calories shouldEqualTo 1000
                dietLabels.size shouldEqualTo 2
                healthLabels.size shouldEqualTo 2
                totalDaily.size shouldEqualTo 2
                totalNutrients.size shouldEqualTo 2
                totalNutrientsKCal.size shouldEqualTo 2
                totalWeight shouldEqualTo 200.0
            }
        }

        runBlocking { nutritionAnalysisViewModel.getNutritionDetails("1 cup rice") }
    }
}