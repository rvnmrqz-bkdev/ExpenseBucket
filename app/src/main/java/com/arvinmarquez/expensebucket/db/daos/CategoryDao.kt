package com.arvinmarquez.expensebucket.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {


    @Query("SELECT * FROM category_table WHERE is_active = 1")
    fun liveCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category_table")
    fun allCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(category: CategoryEntity)

    @Update
    fun updateCategory(category: CategoryEntity)

    @Query("UPDATE category_table SET is_active = 0 WHERE id = :categoryId")
    fun deleteCategory(categoryId: Long)

    @Query("DELETE FROM category_table")
    fun deleteAll()
}