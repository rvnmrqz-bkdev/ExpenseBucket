package com.arvinmarquez.expensebucket.presentation.categories

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.data.repository.CategoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val repo: CategoryRepo
) : ViewModel() {
//
//    val categoryList: LiveData<List<CategoryEntity>>
//
//    init {
//        categoryList = repo.liveCategories
//    }
//
//    fun add(categoryEntity: CategoryEntity) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repo.addCategory(categoryEntity)
//        }
//    }
//
//    fun update(categoryEntity: CategoryEntity): String? {
//        val error = hasEntityError(categoryEntity)
//        if (error == null) {
//            viewModelScope.launch(Dispatchers.IO) {
//                repo.updateCategory(categoryEntity)
//            }
//        }
//        return error
//    }
//
//    fun delete(categoryEntity: CategoryEntity) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repo.deleteCategory(categoryEntity)
//        }
//    }
//
//    private fun hasEntityError(categoryEntity: CategoryEntity): String? {
//        if (TextUtils.isEmpty(categoryEntity.description)) return "Description is empty"
//        return null
//    }

}