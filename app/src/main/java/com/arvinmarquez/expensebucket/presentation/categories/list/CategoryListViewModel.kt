package com.arvinmarquez.expensebucket.presentation.categories.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.features.category.data.use_cases.GetCategoriesUseCase
import com.arvinmarquez.expensebucket.features.category.domain.CategoryListState
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

    companion object {
        val TAG = "CategoryListViewModel"
    }

    private val _dataState = MutableLiveData<CategoryListState>()
    val dataState = _dataState as LiveData<CategoryListState>

    init {
        loadCategoryList()
    }

    private fun loadCategoryList() {
        Log.d(TAG, "loadCategoryList: CALLED")
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.getLiveList().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        Log.d(TAG, "loadCategoryList: LOADING")
                        _dataState.postValue(CategoryListState(isLoading = true))
                    }
                    is Resource.Success -> {
                        Log.d(TAG, "loadCategoryList: SUCCESS")
                        result.data?.collectLatest {
                            _dataState.postValue(CategoryListState(list = it))
                        }
                    }
                    is Resource.Error -> {
                        Log.d(TAG, "loadCategoryList: ERROR")
                        _dataState.postValue(
                            CategoryListState(
                                error = result.message ?: "Unexpected error occurred"
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }

}