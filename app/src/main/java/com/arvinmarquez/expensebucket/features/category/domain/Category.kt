package com.arvinmarquez.expensebucket.features.category.domain

import android.os.Parcelable
import com.arvinmarquez.expensebucket.features.category.data.entities.CategoryEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    var id: Long,
    var description: String,
    var isExpense: Boolean,
    var isActive: Boolean
) : Parcelable {
    override fun toString(): String {
        return description
    }
}

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        description = description,
        isExpense = isExpense,
        isActive = isActive
    )
}