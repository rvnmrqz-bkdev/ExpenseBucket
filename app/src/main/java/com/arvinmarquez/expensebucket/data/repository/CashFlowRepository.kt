package com.arvinmarquez.expensebucket.data.repository


import com.arvinmarquez.expensebucket.data.mapper.CashFlowMapper
import com.arvinmarquez.expensebucket.data.mapper.CashFlowPojoMapper
import com.arvinmarquez.expensebucket.data.mapper.CategoryMapper
import com.arvinmarquez.expensebucket.db.daos.CashFlowCategoryDao
import com.arvinmarquez.expensebucket.db.daos.CashFlowDao
import com.arvinmarquez.expensebucket.domain.CashFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CashFlowRepository @Inject constructor(
    private val cashFlowDao: CashFlowDao,
    private val cashFlowCategoryDao: CashFlowCategoryDao
) {

    fun getLiveCashFlow() =
        cashFlowCategoryDao.liveCashFlowList().map { CashFlowPojoMapper().mapFromEntityList(it) }


    suspend fun getAllCashFlow(): List<CashFlow> {
        return CashFlowPojoMapper().mapFromEntityList(cashFlowCategoryDao.cashFlowList())
    }

    suspend fun insert(cashFlow: CashFlow): Boolean {
        return cashFlowDao.insert(CashFlowMapper().mapToEntity(cashFlow)) > 0
    }

    suspend fun update(cashFlow: CashFlow) {
        cashFlowDao.update(CashFlowMapper().mapToEntity(cashFlow))
    }

    suspend fun delete(cashFlow: CashFlow) {
        cashFlowDao.delete(CashFlowMapper().mapToEntity(cashFlow))
    }
}