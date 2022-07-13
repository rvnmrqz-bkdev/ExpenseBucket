package com.arvinmarquez.expensebucket.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arvinmarquez.expensebucket.db.BucketDatabase
import com.arvinmarquez.expensebucket.db.DbCallback
import com.arvinmarquez.expensebucket.db.daos.CashFlowCategoryDao
import com.arvinmarquez.expensebucket.db.daos.CategoryDao
import com.arvinmarquez.expensebucket.db.daos.CashFlowDao
import com.arvinmarquez.expensebucket.utils.Constants
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