package com.arvinmarquez.expensebucket.features.cashflow.data.data_sources

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.arvinmarquez.expensebucket.features.cashflow.data.entities.CashFlowWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface CashFlowCategoryDao {

    @Transaction
    @Query("SELECT * FROM cash_flow_table ORDER BY date DESC")
    fun cashFlowList(): List<CashFlowWithCategory>

    @Transaction
    @Query("SELECT * FROM cash_flow_table ORDER BY date DESC")
    fun liveCashFlowList(): Flow<List<CashFlowWithCategory>>

}