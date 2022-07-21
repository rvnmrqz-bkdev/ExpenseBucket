package com.arvinmarquez.expensebucket.core.di

import com.arvinmarquez.expensebucket.features.category.domain.use_case.GetCategoriesUseCase
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import com.arvinmarquez.expensebucket.features.category.domain.use_case.AddCategoryUseCase
import com.arvinmarquez.expensebucket.features.category.domain.use_case.DeleteCategoryUseCase
import com.arvinmarquez.expensebucket.features.category.domain.use_case.UpdateCategoryUseCase
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

    @Singleton
    @Provides
    fun provideAddCategoryUseCase(
        repository: CategoryRepository
    ): AddCategoryUseCase = AddCategoryUseCase(repository)

    @Singleton
    @Provides
    fun provideUpdateCategoryUseCase(
        repository: CategoryRepository
    ): UpdateCategoryUseCase = UpdateCategoryUseCase(repository)

    @Singleton
    @Provides
    fun deleteCategoryUseCase(
        repository: CategoryRepository
    ): DeleteCategoryUseCase = DeleteCategoryUseCase(repository)

}