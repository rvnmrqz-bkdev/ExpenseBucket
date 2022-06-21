package com.arvinmarquez.expensebucket.data.repository


import androidx.lifecycle.LiveData
import com.arvinmarquez.expensebucket.db.daos.ExpenseDao
import com.arvinmarquez.expensebucket.data.entities.ExpenseEntity
import com.arvinmarquez.expensebucket.data.pojo.ExpenseWithCategory

class TransactionRepo(
    private val expenseDao: ExpenseDao
) {
    val allExpenseWithCategory : LiveData<List<ExpenseWithCategory>> = expenseDao.liveExpensesWithCategory()

    suspend fun insert(transaction: ExpenseEntity) {
        expenseDao.insert(transaction)
    }

    suspend fun update(transaction: ExpenseEntity) {
        expenseDao.update(transaction)
    }

    suspend fun delete(transaction: ExpenseEntity) {
        expenseDao.delete(transaction)
    }

}