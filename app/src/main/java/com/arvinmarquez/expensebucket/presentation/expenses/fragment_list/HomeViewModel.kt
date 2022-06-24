package com.arvinmarquez.expensebucket.presentation.expenses.fragment_list


import androidx.lifecycle.ViewModel
import com.arvinmarquez.expensebucket.data.repository.ExpenseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    val expenseRepo: ExpenseRepo
) : ViewModel() {

    companion object {
        val TAG = "HomeViewModel"
    }

    var liveList = expenseRepo.allExpenseWithCategory

}