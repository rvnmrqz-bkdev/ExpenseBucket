package com.arvinmarquez.expensebucket.features.category.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Long,
    val description: String,
    val isExpense: Boolean,
    val isActive: Boolean
) : Parcelable {

    override fun toString(): String {
        return description
    }
}