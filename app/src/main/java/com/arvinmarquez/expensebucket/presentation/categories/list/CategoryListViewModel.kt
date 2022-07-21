package com.arvinmarquez.expensebucket.presentation.categories.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.features.category.data.use_cases.GetCategoriesUseCase
import com.arvinmarquez.expensebucket.features.category.domain.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _dataState = MutableLiveData<Resource<List<Category>>>()
    val dataState = _dataState as LiveData<Resource<List<Category>>>

    init {
        loadCategoryList()
    }

    private fun loadCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.getLiveList().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _dataState.postValue(Resource.Loading())
                    }
                    is Resource.Success -> {
                        result.data?.collectLatest {
                            _dataState.postValue(Resource.Success(it))
                        }
                    }
                    is Resource.Error -> {
                        _dataState.postValue(
                            Resource.Error(
                                result.message ?: "Unexpected error occurred"
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }

}