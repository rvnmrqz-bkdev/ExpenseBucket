package com.arvinmarquez.expensebucket.data.repository

import com.arvinmarquez.expensebucket.db.daos.CategoryDao
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import javax.inject.Inject

class CategoryRepo @Inject constructor(
    private val categoryDao: CategoryDao
) {

    val liveCategories = categoryDao.liveCategories()

    suspend fun allCategories(): List<CategoryEntity> {
        return categoryDao.allCategories()
    }

    suspend fun addCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    suspend fun updateCategory(category: CategoryEntity) {
        categoryDao.updateCategory(category)
    }

    suspend fun deleteCategory(category: CategoryEntity) {
        categoryDao.deleteCategory(category.id)
    }

}