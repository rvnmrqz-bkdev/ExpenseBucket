package com.arvinmarquez.expensebucket.core.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arvinmarquez.expensebucket.features.cashflow.data.entities.CashFlowEntity
import com.arvinmarquez.expensebucket.features.category.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowCategoryDao
import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowDao
import com.arvinmarquez.expensebucket.features.category.data.data_sources.CategoryDao


@Database(
    entities = [
        CategoryEntity::class,
        CashFlowEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BucketDatabase : RoomDatabase() {
    //entity dao
    abstract fun categoryDao(): CategoryDao
    abstract fun cashFlowDao(): CashFlowDao

    //pojo-dao
    abstract fun cashFlowCategoryDao(): CashFlowCategoryDao
}