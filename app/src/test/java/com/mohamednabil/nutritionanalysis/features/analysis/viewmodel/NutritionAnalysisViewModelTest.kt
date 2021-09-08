package com.mohamednabil.nutritionanalysis.features.analysis.viewmodel

import com.mohamednabil.core.core.functional.Either.Right
import com.mohamednabil.nutritionanalysis.core.AndroidTest
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutrientsData
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce.Ingredient
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce.NutrientsDataEntity
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce.Parsed
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
            listOf(
                Ingredient(
                    listOf(
                        Parsed(
                            "rice",
                            "food_bpumdjzb5rtqaeabb0kbgbcgr4t9",
                            "rice",
                            "cup",
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
                            emptyMap(),
                            1.0,
                            195.0,
                            "OK",
                            195.0
                        )
                    ),
                    "1 cup rice"
                )
            ),
            mapOf("ENERC_KCAL" to NutrientsDataEntity("Energy", 88.68059870625, "%")),
            195.0
        )
        coEvery { getNutrition.run(any()) } returns Right(nutrientsData.toNutrientsDataView())

        nutritionAnalysisViewModel.nutritionDetails.observeForever {
            with(it!!.data[0]) {
                food shouldEqualTo "rice"
                measure shouldEqualTo "cup"
                calories shouldEqualTo 1000.0
                quantity shouldEqualTo 1.0
                weight shouldEqualTo 195.0
            }
            with(it.totalNutrients) {
                size shouldEqualTo 1
                get(0).label shouldEqualTo "Energy"
                get(0).quantity shouldEqualTo 88.68059870625
                get(0).unit shouldEqualTo "%"
            }
        }

        runBlocking { nutritionAnalysisViewModel.getNutritionDetails(listOf("1 cup rice")) }
    }
}