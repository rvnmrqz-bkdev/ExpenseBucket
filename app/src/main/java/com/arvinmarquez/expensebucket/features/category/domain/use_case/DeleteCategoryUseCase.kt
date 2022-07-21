package com.arvinmarquez.expensebucket.features.category.domain.use_case

import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {

    fun delete(category: Category): Flow<Resource<Category>> = flow {
        try {
            emit(Resource.Loading())

            if (repository.delete(category) > 0) emit(Resource.Success(category))
            else emit(Resource.Error("Failed to delete. Item not found"))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}