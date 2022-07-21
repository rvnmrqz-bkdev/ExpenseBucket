package com.arvinmarquez.expensebucket.features.cashflow.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.arvinmarquez.expensebucket.features.cashflow.domain.CashFlow
import com.arvinmarquez.expensebucket.features.category.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.features.category.data.entities.toDomain
import com.arvinmarquez.expensebucket.features.category.domain.Category

data class CashFlowWithCategory(
    @Embedded
    val cashFlowEntity: CashFlowEntity,

    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity?
)

fun CashFlowWithCategory.toDomain(): CashFlow {
    return CashFlow(
        id = cashFlowEntity.id,
        description =  cashFlowEntity.description,
        category =  categoryEntity?.toDomain(),
        amount = cashFlowEntity.amount,
        date = cashFlowEntity.date
    )
}
