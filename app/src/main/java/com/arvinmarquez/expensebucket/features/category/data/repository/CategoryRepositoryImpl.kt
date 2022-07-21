package com.arvinmarquez.expensebucket.features.category.data.repository

import com.arvinmarquez.expensebucket.features.category.data.data_sources.CategoryDao
import com.arvinmarquez.expensebucket.features.category.data.entities.toDomain
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import com.arvinmarquez.expensebucket.features.category.domain.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override suspend fun getLiveCategories(): Flow<List<Category>> =
        categoryDao.liveCategories().map { it.map { e -> e.toDomain() } }

    override suspend fun getCategories(): List<Category> =
        categoryDao.allCategories().map { it.toDomain() }

    override suspend fun insert(category: Category) =
        categoryDao.insertCategory(category.toEntity())

    override suspend fun update(category: Category) =
        categoryDao.updateCategory(category.toEntity())

    override suspend fun delete(category: Category) =
        categoryDao.deleteCategory(category.id)

}