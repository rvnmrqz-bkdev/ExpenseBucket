package com.arvinmarquez.expensebucket.features.cashflow.data.repository


import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowCategoryDao
import com.arvinmarquez.expensebucket.features.cashflow.data.data_sources.CashFlowDao
import com.arvinmarquez.expensebucket.features.cashflow.data.mapper.CashFlowMapper
import com.arvinmarquez.expensebucket.features.cashflow.data.mapper.CashFlowWithCategoryMapper
import com.arvinmarquez.expensebucket.features.cashflow.domain.CashFlow
import com.arvinmarquez.expensebucket.features.cashflow.domain.repository.CashFlowRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CashFlowRepositoryImpl @Inject constructor(
    private val cashFlowDao: CashFlowDao,
    private val cashFlowCategoryDao: CashFlowCategoryDao
) : CashFlowRepository {

    override fun getLiveCashFlow() =
        cashFlowCategoryDao.liveCashFlowList().map { CashFlowWithCategoryMapper().mapFromEntityList(it) }

    override suspend fun getAllCashFlow(): List<CashFlow> {
        return CashFlowWithCategoryMapper().mapFromEntityList(cashFlowCategoryDao.cashFlowList())
    }

    override suspend fun insert(cashFlow: CashFlow) {
        cashFlowDao.insert(CashFlowMapper().mapToEntity(cashFlow))
    }

    override suspend fun update(cashFlow: CashFlow) {
        cashFlowDao.update(CashFlowMapper().mapToEntity(cashFlow))
    }

    override suspend fun delete(cashFlow: CashFlow) {
        cashFlowDao.delete(CashFlowMapper().mapToEntity(cashFlow))
    }
}