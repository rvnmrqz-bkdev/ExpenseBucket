package com.arvinmarquez.expensebucket.db


import androidx.room.*
import com.arvinmarquez.expensebucket.db.daos.CategoryDao
import com.arvinmarquez.expensebucket.db.daos.ExpenseDao
import com.arvinmarquez.expensebucket.data.entities.ExpenseEntity
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity


@Database(
    entities = [
        CategoryEntity::class,
        ExpenseEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BucketDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao


}