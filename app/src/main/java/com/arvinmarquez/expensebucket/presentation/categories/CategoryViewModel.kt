package com.arvinmarquez.expensebucket.presentation.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.data.repository.CategoryRepository
import com.arvinmarquez.expensebucket.domain.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    companion object {
        private val TAG = "CategoryViewModel"
    }

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList = _categoryList as LiveData<List<Category>>

    init {
        getCategoryList()
    }

    private fun getCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getLiveCategories().collectLatest {
                _categoryList.postValue(it)
            }
        }
    }

    fun add(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.addCategory(category)
        }
    }

    fun update(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.updateCategory(category)
        }
    }

    fun delete(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.deleteCategory(category)
        }
    }


}