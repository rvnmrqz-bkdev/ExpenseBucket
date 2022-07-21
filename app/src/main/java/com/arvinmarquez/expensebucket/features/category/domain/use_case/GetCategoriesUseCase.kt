package com.arvinmarquez.expensebucket.features.category.domain.use_case

import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {

    companion object {
        val TAG = "GetCategoriesUseCase"
    }

    fun getLiveList(): Flow<Resource<Flow<List<Category>>>> = flow {
        try {
            emit(Resource.Loading())
            val list = repository.getLiveCategories()
            emit(Resource.Success(list))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }

    fun getList() :Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading())
            val list = repository.getCategories()
            emit(Resource.Success(list))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}