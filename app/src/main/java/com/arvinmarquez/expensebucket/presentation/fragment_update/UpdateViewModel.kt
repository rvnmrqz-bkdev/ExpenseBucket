package com.arvinmarquez.expensebucket.presentation.fragment_update


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.data.entities.ExpenseEntity
import com.arvinmarquez.expensebucket.data.repository.CategoryRepo
import com.arvinmarquez.expensebucket.data.repository.ExpenseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    val expenseRepo: ExpenseRepo,
    val categoryRepo: CategoryRepo
) : ViewModel() {

    companion object {
        val TAG = "UpdateViewModel"
    }

    val categoryOptions: LiveData<List<CategoryEntity>>
    var selectedCategoryId: Long = 0

    init {
        categoryOptions = categoryRepo.liveCategories
    }

    fun update(expense: ExpenseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepo.update(expense)
        }
    }

    fun deleteItem(expense: ExpenseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepo.delete(expense)
        }
    }

}