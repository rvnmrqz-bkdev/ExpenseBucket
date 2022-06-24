package com.arvinmarquez.expensebucket.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.db.daos.CategoryDao
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
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Uncategorized", "-", 1))
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Food", "-", 1))
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Transportation", "-", 1))
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Bill", "-", 1))
        categoryDaoProvider.get().insertCategory(CategoryEntity(0, "Personal", "-", 1))
    }
}