package com.arvinmarquez.expensebucket.features.cashflow.data.repository


import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowCategoryDao
import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowDao
import com.arvinmarquez.expensebucket.features.cashflow.data.entities.toDomain
import com.arvinmarquez.expensebucket.features.cashflow.domain.CashFlow
import com.arvinmarquez.expensebucket.features.cashflow.domain.repository.CashFlowRepository
import com.arvinmarquez.expensebucket.features.cashflow.domain.toEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CashFlowRepositoryImpl @Inject constructor(
    private val cashFlowDao: CashFlowDao,
    private val cashFlowCategoryDao: CashFlowCategoryDao
) : CashFlowRepository {

    override fun getLiveCashFlow() =
        cashFlowCategoryDao.liveCashFlowList().map { list -> list.map { it.toDomain() } }

    override suspend fun getAllCashFlow(): List<CashFlow> {
        return cashFlowCategoryDao.cashFlowList().map { it.toDomain() }
    }

    override suspend fun insert(cashFlow: CashFlow) {
        cashFlowDao.insert(cashFlow.toEntity())
    }

    override suspend fun update(cashFlow: CashFlow) {
        cashFlowDao.update(cashFlow.toEntity())
    }

    override suspend fun delete(cashFlow: CashFlow) {
        cashFlowDao.delete(cashFlow.toEntity())
    }
}