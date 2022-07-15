package com.arvinmarquez.expensebucket.features.category.data.repository

import com.arvinmarquez.expensebucket.features.category.data.data_sources.CategoryDao
import com.arvinmarquez.expensebucket.features.category.data.mapper.CategoryMapper
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getLiveCategories() =
        categoryDao.liveCategories().map { CategoryMapper().mapFromEntityList(it) }

    override suspend fun insert(category: Category) {
        categoryDao.insertCategory(CategoryMapper().mapToEntity(category))
    }

    override suspend fun update(category: Category) {
        categoryDao.updateCategory(CategoryMapper().mapToEntity(category))
    }

    override suspend fun delete(category: Category) {
        categoryDao.deleteCategory(category.id)
    }
}