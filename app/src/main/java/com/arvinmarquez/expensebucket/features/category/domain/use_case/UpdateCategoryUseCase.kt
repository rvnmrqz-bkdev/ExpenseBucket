package com.arvinmarquez.expensebucket.features.category.domain.use_case

import android.text.TextUtils
import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {

    fun update(category: Category): Flow<Resource<Category>> = flow {
        try {
            emit(Resource.Loading())

            if (TextUtils.isEmpty(category.description.trim())) {
                emit(Resource.Error("Description must not be empty"))
                return@flow
            }

            if (repository.update(category) > 0) emit(Resource.Success(category))
            else emit(Resource.Error("Update failed. Item not found"))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}