package com.arvinmarquez.expensebucket.db.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.arvinmarquez.expensebucket.data.pojo.CashFlowWithCategory
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