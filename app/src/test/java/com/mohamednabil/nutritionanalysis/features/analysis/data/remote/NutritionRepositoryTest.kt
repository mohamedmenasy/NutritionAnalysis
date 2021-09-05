package com.mohamednabil.nutritionanalysis.features.analysis.data.remote

import com.mohamednabil.nutritionanalysis.core.UnitTest
import com.mohamednabil.nutritionanalysis.core.exception.Failure
import com.mohamednabil.nutritionanalysis.core.extension.empty
import com.mohamednabil.nutritionanalysis.core.functional.Either
import com.mohamednabil.nutritionanalysis.core.platform.NetworkHandler
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutritionRepository.Network
import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.responce.NutritionDetailsEntity
import io.mockk.Called
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class NutritionRepositoryTest : UnitTest() {
    private lateinit var networkRepository: Network

    @MockK
    private lateinit var networkHandler: NetworkHandler

    @MockK
    private lateinit var service: NutritionService

    @MockK
    private lateinit var getNutritionCall: Call<NutritionDetailsEntity>

    @MockK
    private lateinit var getNutritionResponse: Response<NutritionDetailsEntity>

    @Before
    fun setUp() {
        networkRepository = Network(networkHandler, service)
    }

    @Test
    fun `given networkHandler, getNutritionResponse, getNutritionCall when call getNutritionsForIngredients then return empty NutrientsData object by default`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { getNutritionResponse.body() } returns null
        every { getNutritionResponse.isSuccessful } returns true
        every { getNutritionCall.execute() } returns getNutritionResponse
        every { service.getNutrition("1 cup rice") } returns getNutritionCall

        val nutritions = networkRepository.getNutritionsForIngredients("1 cup rice")

        nutritions shouldEqual Either.Right(NutrientsData.empty)
        verify(exactly = 1) { service.getNutrition("1 cup rice") }
    }

    @Test
    fun `given networkHandler, getNutritionResponse when call getNutritionsForIngredients then get NutrientsData object from service`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { getNutritionResponse.body() } returns NutritionDetailsEntity(
            1500,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyMap(),
            emptyMap(),
            emptyMap(),
            50.0,
            String.empty()
        )
        every { getNutritionResponse.isSuccessful } returns true
        every { getNutritionCall.execute() } returns getNutritionResponse
        every { service.getNutrition("1 cup rice") } returns getNutritionCall

        val nutritions = networkRepository.getNutritionsForIngredients("1 cup rice")

        nutritions shouldEqual Either.Right(
            NutrientsData(
                1500,
                emptyList(),
                emptyList(),
                emptyMap(),
                emptyMap(),
                emptyMap(),
                50.0
            )
        )
        verify(exactly = 1) { service.getNutrition("1 cup rice") }
    }

    @Test
    fun `given networkHandler when call nutrition service then return network failure when no connection`() {
        every { networkHandler.isNetworkAvailable() } returns false

        val nutritions = networkRepository.getNutritionsForIngredients("1 cup rice")

        nutritions shouldBeInstanceOf Either::class.java
        nutritions.isLeft shouldEqual true
        nutritions.fold(
            { failure -> failure shouldBeInstanceOf Failure.NetworkConnection::class.java },
            {})
        verify { service wasNot Called }
    }

    @Test
    fun `given networkHandler, getNutritionResponse, getNutritionCall when call nutrition service with no successful response then return server error`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { getNutritionResponse.isSuccessful } returns false
        every { getNutritionCall.execute() } returns getNutritionResponse
        every { service.getNutrition("1 cup rice") } returns getNutritionCall

        val nutritions = networkRepository.getNutritionsForIngredients("1 cup rice")

        nutritions shouldBeInstanceOf Either::class.java
        nutritions.isLeft shouldEqual true
        nutritions.fold(
            { failure -> failure shouldBeInstanceOf Failure.ServerError::class.java },
            {})
    }

    @Test
    fun `given networkHandler, getNutritionResponse, getNutritionCall when call nutrition with an exceptions then catch exceptions`() {
        every { networkHandler.isNetworkAvailable() } returns true
        every { getNutritionCall.execute() } returns getNutritionResponse
        every { service.getNutrition("1 cup rice") } returns getNutritionCall

        val nutritions = networkRepository.getNutritionsForIngredients("1 cup rice")

        nutritions shouldBeInstanceOf Either::class.java
        nutritions.isLeft shouldEqual true
        nutritions.fold(
            { failure -> failure shouldBeInstanceOf Failure.ServerError::class.java },
            {})
    }
}