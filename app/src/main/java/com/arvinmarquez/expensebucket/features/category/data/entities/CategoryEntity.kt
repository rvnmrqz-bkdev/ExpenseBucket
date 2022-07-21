package com.arvinmarquez.expensebucket.features.category.data.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arvinmarquez.expensebucket.features.category.domain.Category

@Entity(tableName = "category_table")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "is_expense", defaultValue = "1")
    val isExpense: Boolean,

    @ColumnInfo(name = "is_active", defaultValue = "1")
    val isActive: Boolean
)

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        description = description,
        isExpense = isExpense,
        isActive = isActive
    )
}