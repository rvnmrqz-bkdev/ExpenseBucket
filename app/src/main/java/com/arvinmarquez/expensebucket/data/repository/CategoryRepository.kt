package com.arvinmarquez.expensebucket.data.repository

import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.data.mapper.CategoryMapper
import com.arvinmarquez.expensebucket.db.daos.CategoryDao
import com.arvinmarquez.expensebucket.domain.Category
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {

    fun getLiveCategories() = categoryDao.liveCategories().map { CategoryMapper().mapFromEntityList(it) }

    suspend fun allCategories(): List<CategoryEntity> {
        return categoryDao.allCategories()
    }

    suspend fun addCategory(category: Category) {
        categoryDao.insertCategory(CategoryMapper().mapToEntity(category))
    }

    suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(CategoryMapper().mapToEntity(category))
    }

    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category.id)
    }

}