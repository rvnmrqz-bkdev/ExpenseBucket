package com.arvinmarquez.expensebucket.ui.fragment_add

import android.app.Application
import android.text.TextUtils
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
import java.util.*

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val transactionRepo: TransactionRepo
    private val categoryRepo: CategoryRepo
    val allCategories: LiveData<List<CategoryEntity>>

    var description: String = ""
    var categoryId: Long = 0
    var amount: Double? = 0.0

    init {
        val transactionDao =
            BucketDatabase.getInstance(application, viewModelScope).moneyTransactionDao()
        val categoryDao = BucketDatabase.getInstance(application, viewModelScope).categoryDao()
        transactionRepo = TransactionRepo(transactionDao)
        categoryRepo = CategoryRepo(categoryDao)
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
            transactionRepo.insert(transaction)
        }
    }
}