package com.arvinmarquez.expensebucket.di

import com.arvinmarquez.expensebucket.data.repository.CategoryRepository
import com.arvinmarquez.expensebucket.data.repository.CashFlowRepository
import com.arvinmarquez.expensebucket.db.daos.CashFlowCategoryDao
import com.arvinmarquez.expensebucket.db.daos.CategoryDao
import com.arvinmarquez.expensebucket.db.daos.CashFlowDao
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
    fun providesCashFlowRepository(dao: CashFlowDao, pojoDao: CashFlowCategoryDao): CashFlowRepository {
        return CashFlowRepository(dao, pojoDao)
    }

    @Singleton
    @Provides
    fun providesCategoryRepository(dao: CategoryDao): CategoryRepository {
        return CategoryRepository(dao)
    }

}