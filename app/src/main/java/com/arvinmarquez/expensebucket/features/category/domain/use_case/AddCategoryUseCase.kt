package com.arvinmarquez.expensebucket.features.category.domain.use_case

import android.text.TextUtils
import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {

    fun add(category: Category): Flow<Resource<Category>> = flow {
        try {
            emit(Resource.Loading())

            if (TextUtils.isEmpty(category.description.trim())){
                emit(Resource.Error("Description must not be empty"))
                return@flow
            }

            category.id = repository.insert(category)
            emit(Resource.Success(category))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}