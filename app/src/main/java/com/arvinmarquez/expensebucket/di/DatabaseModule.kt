package com.arvinmarquez.expensebucket.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arvinmarquez.expensebucket.db.BucketDatabase
import com.arvinmarquez.expensebucket.db.DbCallback
import com.arvinmarquez.expensebucket.db.daos.CategoryDao
import com.arvinmarquez.expensebucket.db.daos.ExpenseDao
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
        provider: Provider<CategoryDao>
    ): BucketDatabase {
        return Room.databaseBuilder(
            context,
            BucketDatabase::class.java,
            Constants.Database.DATABASE_NAME
        )
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .addCallback(DbCallback(provider))
            .build()
    }

    @Provides
    fun providesExpenseDao(database: BucketDatabase): ExpenseDao = database.expenseDao()

    @Provides
    fun providesCategoryDao(database: BucketDatabase): CategoryDao = database.categoryDao()
}