package com.arvinmarquez.expensebucket.features.cashflow.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.arvinmarquez.expensebucket.features.category.data.entities.CategoryEntity

data class CashFlowWithCategory (
    @Embedded
    val cashFlowEntity : CashFlowEntity,

    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity?
)