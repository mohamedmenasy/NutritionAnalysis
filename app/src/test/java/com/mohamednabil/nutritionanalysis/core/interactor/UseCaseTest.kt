package com.mohamednabil.nutritionanalysis.core.interactor

import com.mohamednabil.nutritionanalysis.core.AndroidTest
import com.mohamednabil.nutritionanalysis.core.functional.Either.Right
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Test

class UseCaseTest : AndroidTest() {

    private val useCase = MyUseCase()

    @Test
    fun `running use case should return 'Either' of use case type`() {
        val params = TestParams(TYPE_PARAM)
        val result = runBlocking { useCase.run(params) }

        result shouldEqual Right(TestType(TYPE_TEST))
    }

    data class TestType(val name: String)
    data class TestParams(val name: String)

    private inner class MyUseCase : UseCase<TestType, TestParams>() {
        override suspend fun run(params: TestParams) = Right(TestType(TYPE_TEST))
    }

    companion object {
        private const val TYPE_TEST = "Test"
        private const val TYPE_PARAM = "ParamTest"
    }
}
