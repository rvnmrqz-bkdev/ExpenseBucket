package com.arvinmarquez.expensebucket.features.cashflow.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.arvinmarquez.expensebucket.features.category.data.entities.CategoryEntity
import java.util.*

@Entity(
    tableName = "cash_flow_table",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CashFlowEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "category_id", index = true)
    val categoryId: Long,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "date", defaultValue = "CURRENT_TIMESTAMP")
    val date: Date
)