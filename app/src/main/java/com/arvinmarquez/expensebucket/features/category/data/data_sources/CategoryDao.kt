package com.arvinmarquez.expensebucket.features.category.data.data_sources

import androidx.room.*
import com.arvinmarquez.expensebucket.features.category.data.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table WHERE is_active = 1")
    fun liveCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category_table WHERE is_active = 1")
    fun allCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(category: CategoryEntity): Long

    @Update
    fun updateCategory(category: CategoryEntity): Int

    @Query("UPDATE category_table SET is_active = 0 WHERE id = :categoryId")
    fun deleteCategory(categoryId: Long): Int

    @Query("DELETE FROM category_table")
    fun deleteAll()
}