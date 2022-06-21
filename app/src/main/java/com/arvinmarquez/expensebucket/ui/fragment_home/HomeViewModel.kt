package com.arvinmarquez.expensebucket.ui.fragment_home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.data.repository.TransactionRepo
import com.arvinmarquez.expensebucket.db.BucketDatabase
import com.arvinmarquez.expensebucket.data.pojo.ExpenseWithCategory

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val liveList: LiveData<List<ExpenseWithCategory>>
    private val transactionRepo: TransactionRepo

    init {
        val transactionDao = BucketDatabase.getInstance(application, viewModelScope).moneyTransactionDao()
        transactionRepo = TransactionRepo(transactionDao)
        liveList = transactionRepo.allExpenseWithCategory
    }

}