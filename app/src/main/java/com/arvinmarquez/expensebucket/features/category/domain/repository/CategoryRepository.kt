package com.arvinmarquez.expensebucket.features.category.domain.repository

import com.arvinmarquez.expensebucket.features.category.domain.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getLiveCategories(): Flow<List<Category>>

    suspend fun insert(category: Category)

    suspend fun update(category: Category)

    suspend fun delete(category: Category)

}