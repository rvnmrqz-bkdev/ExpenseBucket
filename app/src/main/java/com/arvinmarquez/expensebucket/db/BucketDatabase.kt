package com.arvinmarquez.expensebucket.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arvinmarquez.expensebucket.data.entities.CashFlowEntity
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.db.daos.CashFlowCategoryDao
import com.arvinmarquez.expensebucket.db.daos.CashFlowDao
import com.arvinmarquez.expensebucket.db.daos.CategoryDao


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