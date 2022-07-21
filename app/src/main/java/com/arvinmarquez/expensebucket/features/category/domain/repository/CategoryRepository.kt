package com.arvinmarquez.expensebucket.features.category.domain.repository

import com.arvinmarquez.expensebucket.features.category.domain.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getLiveCategories(): Flow<List<Category>>

    suspend fun getCategories(): List<Category>

    suspend fun insert(category: Category): Long

    suspend fun update(category: Category): Int

    suspend fun delete(category: Category): Int
}