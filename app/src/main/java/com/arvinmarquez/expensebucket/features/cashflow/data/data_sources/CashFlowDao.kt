package com.arvinmarquez.expensebucket.features.cashflow.data.data_sources

import androidx.room.*
import com.arvinmarquez.expensebucket.features.cashflow.data.entities.CashFlowEntity

@Dao
interface CashFlowDao {

    @Query("SELECT * FROM cash_flow_table ORDER BY date DESC")
    fun allExpenses(): List<CashFlowEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(transaction: CashFlowEntity): Long

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(transaction: CashFlowEntity)

    @Delete
    fun delete(transaction: CashFlowEntity)

}