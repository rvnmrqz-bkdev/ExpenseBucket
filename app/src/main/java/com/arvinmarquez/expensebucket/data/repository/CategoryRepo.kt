package com.arvinmarquez.expensebucket.data.repository

import com.arvinmarquez.expensebucket.db.daos.CategoryDao
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity

class CategoryRepo(
    val dao: CategoryDao
) {

    val liveCategories = dao.liveCategories()

    suspend fun allCategories() : List<CategoryEntity> {
        return dao.allCategories()
    }

    suspend fun addCategory(category: CategoryEntity) {
        dao.insertCategory(category)
    }

    suspend fun updateCategory(category: CategoryEntity) {
        dao.updateCategory(category)
    }

    suspend fun deleteCategory(category: CategoryEntity) {
        dao.deleteCategory(category.id)
    }

}