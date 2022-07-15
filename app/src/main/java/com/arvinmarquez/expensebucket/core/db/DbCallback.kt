package com.arvinmarquez.expensebucket.core.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arvinmarquez.expensebucket.features.category.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.features.category.data.data_sources.CategoryDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class DbCallback(
    private val categoryDaoProvider: Provider<CategoryDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Uncategorized", true, true))
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Food & Groceries", true, true))
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Transportation", true, true))
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Housing/Rent", true, true))
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Utility Bills", true, true))
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Salary", false, true))
    }
}