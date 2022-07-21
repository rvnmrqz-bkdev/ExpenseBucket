package com.arvinmarquez.expensebucket.core.di

import com.arvinmarquez.expensebucket.features.category.data.use_cases.GetCategoriesUseCase
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCategoriesUseCase(
        repository: CategoryRepository
    ): GetCategoriesUseCase = GetCategoriesUseCase(repository)

}