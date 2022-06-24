package com.arvinmarquez.expensebucket.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arvinmarquez.expensebucket.data.entities.ExpenseEntity
import com.arvinmarquez.expensebucket.data.pojo.ExpenseWithCategory

@Dao
interface ExpenseDao {

    @Transaction
    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    fun liveExpensesWithCategory() : LiveData<List<ExpenseWithCategory>>

    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    fun allExpenses() : List<ExpenseEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(transaction: ExpenseEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(transaction: ExpenseEntity)

    @Delete
    fun delete(transaction: ExpenseEntity)

}