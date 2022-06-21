package com.arvinmarquez.expensebucket.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arvinmarquez.expensebucket.db.daos.CategoryDao
import com.arvinmarquez.expensebucket.db.daos.ExpenseDao
import com.arvinmarquez.expensebucket.data.entities.ExpenseEntity
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val DATABASE = "bucket_database"

@Database(
    entities = [
        CategoryEntity::class,
        ExpenseEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BucketDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun moneyTransactionDao(): ExpenseDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: BucketDatabase? = null

        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): BucketDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context, scope).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, scope: CoroutineScope): BucketDatabase {
            return Room
                .databaseBuilder(context, BucketDatabase::class.java, DATABASE)
                .addCallback(DatabaseCallback(scope))
                .build()
        }
    }

    class DatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            instance?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.categoryDao())
                }
            }
        }

        private suspend fun populateDatabase(categoryDao: CategoryDao) {
            // Delete all content here.
            categoryDao.deleteAll()

            //insert default
            categoryDao.insertCategory(CategoryEntity(0,"Food","-",1))
            categoryDao.insertCategory(CategoryEntity(0,"Transportation","-",1))
            categoryDao.insertCategory(CategoryEntity(0,"Personal","-",1))
            categoryDao.insertCategory(CategoryEntity(0,"Income","+",1))
            categoryDao.insertCategory(CategoryEntity(0,"Savings","-",1))
        }
    }

}