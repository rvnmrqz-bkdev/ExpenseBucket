package com.arvinmarquez.expensebucket.data.pojo

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.data.entities.CashFlowEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class CashFlowWithCategory (
    @Embedded
    val cashFlowEntity : CashFlowEntity,

    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity?
) : Parcelable