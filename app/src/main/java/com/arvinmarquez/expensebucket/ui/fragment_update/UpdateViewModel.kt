package com.arvinmarquez.expensebucket.ui.fragment_update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.data.entities.ExpenseEntity
import com.arvinmarquez.expensebucket.data.repository.CategoryRepo
import com.arvinmarquez.expensebucket.data.repository.TransactionRepo
import com.arvinmarquez.expensebucket.db.BucketDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        val TAG = "UpdateViewModel"
    }

    private val transactionRepo: TransactionRepo
    private val categoryRepo: CategoryRepo

    val categoryOptions: LiveData<List<CategoryEntity>>
    var selectedCategoryId : Long = 0

    init {
        val transactionDao =
            BucketDatabase.getInstance(application, viewModelScope).moneyTransactionDao()
        transactionRepo = TransactionRepo(transactionDao)
        val categoryDao = BucketDatabase.getInstance(application, viewModelScope).categoryDao()
        categoryRepo = CategoryRepo(categoryDao)
        categoryOptions = categoryRepo.liveCategories
    }

    fun updateTransaction(expense: ExpenseEntity){
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepo.update(expense)
        }
    }

}