package com.arvinmarquez.expensebucket.features.cashflow.domain.repository

import com.arvinmarquez.expensebucket.features.cashflow.domain.CashFlow
import kotlinx.coroutines.flow.Flow

interface CashFlowRepository {

    fun getLiveCashFlow() : Flow<List<CashFlow>>

    suspend fun getAllCashFlow(): List<CashFlow>

    suspend fun insert(cashFlow: CashFlow)

    suspend fun update(cashFlow: CashFlow)

    suspend fun delete(cashFlow: CashFlow)
}