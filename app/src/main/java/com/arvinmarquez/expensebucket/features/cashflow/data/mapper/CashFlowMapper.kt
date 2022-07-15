package com.arvinmarquez.expensebucket.features.cashflow.data.mapper

import com.arvinmarquez.expensebucket.features.cashflow.data.entities.CashFlowEntity
import com.arvinmarquez.expensebucket.features.cashflow.domain.CashFlow

class CashFlowMapper {

    fun mapToEntity(domain: CashFlow): CashFlowEntity {
        return CashFlowEntity(
            id = domain.id,
            description = domain.description,
            categoryId = domain.category?.id ?: 0,
            amount = domain.amount,
            date = domain.date
        )
    }

    fun mapToEntityList(initial: List<CashFlow>): List<CashFlowEntity> {
        return initial.map { mapToEntity(it) }
    }

}