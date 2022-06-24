package com.arvinmarquez.expensebucket.presentation.fragment_add

import android.text.TextUtils
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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    val expenseRepo: ExpenseRepo,
    val categoryRepo: CategoryRepo
) : ViewModel() {


    val allCategories: LiveData<List<CategoryEntity>>

    var description: String = ""
    var categoryId: Long = 0
    var amount: Double? = 0.0

    init {
        allCategories = categoryRepo.liveCategories
    }

    fun saveTransaction(): String? {
        if (TextUtils.isEmpty(description)) return "Empty Description"
        if (amount == null || amount == 0.0) return "Amount is 0"

        addTransaction(
            ExpenseEntity(
                0,
                description,
                categoryId,
                amount!!,
                Calendar.getInstance().time
            )
        )
        return null
    }

    private fun addTransaction(transaction: ExpenseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepo.insert(transaction)
        }
    }
}