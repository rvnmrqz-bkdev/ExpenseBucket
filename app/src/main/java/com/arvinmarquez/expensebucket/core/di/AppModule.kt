package com.arvinmarquez.expensebucket.core.di

import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowCategoryDao
import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowDao
import com.arvinmarquez.expensebucket.features.cashflow.data.repository.CashFlowRepositoryImpl
import com.arvinmarquez.expensebucket.features.cashflow.domain.repository.CashFlowRepository
import com.arvinmarquez.expensebucket.features.category.data.data_sources.CategoryDao
import com.arvinmarquez.expensebucket.features.category.data.repository.CategoryRepositoryImpl
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesCashFlowRepository(
        dao: CashFlowDao,
        pojoDao: CashFlowCategoryDao
    ): CashFlowRepository = CashFlowRepositoryImpl(dao, pojoDao)


    @Singleton
    @Provides
    fun providesCategoryRepository(dao: CategoryDao): CategoryRepository =
        CategoryRepositoryImpl(dao)

}