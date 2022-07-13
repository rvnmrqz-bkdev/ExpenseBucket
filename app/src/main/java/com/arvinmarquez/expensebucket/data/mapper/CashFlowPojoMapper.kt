package com.arvinmarquez.expensebucket.data.mapper

import com.arvinmarquez.expensebucket.data.entities.CashFlowEntity
import com.arvinmarquez.expensebucket.data.pojo.CashFlowWithCategory
import com.arvinmarquez.expensebucket.domain.CashFlow
import com.arvinmarquez.expensebucket.utils.EntityMapper

class CashFlowPojoMapper : EntityMapper<CashFlowWithCategory, CashFlow> {

    override fun mapFromEntity(pojo: CashFlowWithCategory): CashFlow {

        return CashFlow(
            id = pojo.cashFlowEntity.id,
            description = pojo.cashFlowEntity.description,
            category = pojo.categoryEntity?.let { CategoryMapper().mapFromEntity(it) },
            amount = pojo.cashFlowEntity.amount,
            date = pojo.cashFlowEntity.date
        )
    }

    override fun mapToEntity(domain: CashFlow): CashFlowWithCategory {
        return CashFlowWithCategory(
            CashFlowEntity(
                id = domain.id,
                description = domain.description,
                categoryId = domain.category?.id ?: 0,
                amount = domain.amount,
                date = domain.date
            ),
            domain.category?.let { CategoryMapper().mapToEntity(it) }
        )
    }

    override fun mapFromEntityList(initial: List<CashFlowWithCategory>): List<CashFlow> {
        return initial.map { mapFromEntity(it) }
    }

    override fun mapToEntityList(initial: List<CashFlow>): List<CashFlowWithCategory> {
        return initial.map { mapToEntity(it) }
    }

}