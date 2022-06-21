package com.arvinmarquez.expensebucket.data.pojo

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.data.entities.ExpenseEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExpenseWithCategory (
    @Embedded
    val expense : ExpenseEntity,

    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: CategoryEntity?
) : Parcelable