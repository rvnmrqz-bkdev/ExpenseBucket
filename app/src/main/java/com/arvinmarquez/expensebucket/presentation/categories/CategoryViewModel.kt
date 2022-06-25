package com.arvinmarquez.expensebucket.presentation.categories

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.data.repository.CategoryRepo
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject constructor(
    val categoryRepo: CategoryRepo
) : ViewModel() {

    companion object {
        private val TAG = "CategoryViewModel"
    }

    val categoryList: LiveData<List<CategoryEntity>> = categoryRepo.liveCategories

    fun add(category: CategoryEntity): String? {
        val error = hasEntityError(category)
        if (error == null) {
            viewModelScope.launch(Dispatchers.IO) {
                categoryRepo.addCategory(category)
            }
        }
        return error
    }

    fun update(category: CategoryEntity): String? {
        val error = hasEntityError(category)
        if (error == null) {
            viewModelScope.launch(Dispatchers.IO) {
                Log.d(TAG, "update: " + Gson().toJson(category))
                categoryRepo.updateCategory(category)
            }
        }
        return error
    }

    fun delete(category: CategoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepo.deleteCategory(category)
        }
    }

    private fun hasEntityError(category: CategoryEntity): String? {
        if (TextUtils.isEmpty(category.description)) return "No description"
        return null
    }
}