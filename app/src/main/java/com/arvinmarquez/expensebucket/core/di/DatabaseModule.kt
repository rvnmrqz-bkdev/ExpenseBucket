package com.arvinmarquez.expensebucket.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arvinmarquez.expensebucket.core.db.BucketDatabase
import com.arvinmarquez.expensebucket.core.db.DbCallback
import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowCategoryDao
import com.arvinmarquez.expensebucket.features.category.data.data_sources.CategoryDao
import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowDao
import com.arvinmarquez.expensebucket.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context,
        categoryDaoProvider: Provider<CategoryDao>
    ): BucketDatabase {
        return Room.databaseBuilder(
            context,
            BucketDatabase::class.java,
            Constants.Database.DATABASE_NAME
        )
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .addCallback(DbCallback(categoryDaoProvider))
            .build()
    }

    //entity dao
    @Provides
    fun providesCashFlowDao(database: BucketDatabase): CashFlowDao = database.cashFlowDao()

    @Provides
    fun providesCategoryDao(database: BucketDatabase): CategoryDao = database.categoryDao()

    //pojo dao
    @Provides
    fun providesCashFlowCategoryDao(database: BucketDatabase) : CashFlowCategoryDao = database.cashFlowCategoryDao()
}