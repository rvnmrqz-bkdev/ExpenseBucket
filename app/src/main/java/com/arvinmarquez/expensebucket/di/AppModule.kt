package com.arvinmarquez.expensebucket.di

import com.arvinmarquez.expensebucket.data.repository.CategoryRepo
import com.arvinmarquez.expensebucket.data.repository.ExpenseRepo
import com.arvinmarquez.expensebucket.db.daos.CategoryDao
import com.arvinmarquez.expensebucket.db.daos.ExpenseDao
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
    fun providesExpenseRepository(dao: ExpenseDao): ExpenseRepo {
        return ExpenseRepo(dao)
    }

    @Singleton
    @Provides
    fun providesCategoryRepository(dao: CategoryDao): CategoryRepo {
        return CategoryRepo(dao)
    }

}