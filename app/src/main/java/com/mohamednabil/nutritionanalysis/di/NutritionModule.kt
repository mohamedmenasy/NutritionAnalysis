package com.mohamednabil.nutritionanalysis.di

import com.mohamednabil.nutritionanalysis.features.analysis.data.remote.NutritionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NutritionModule {
    @Provides
    @Singleton
    fun provideNutritionRepository(dataSource: NutritionRepository.Network): NutritionRepository =
        dataSource
}
