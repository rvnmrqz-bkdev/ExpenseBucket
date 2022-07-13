package com.arvinmarquez.expensebucket.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Category(
    val id: Long,
    val description: String,
    val isExpense: Boolean,
    val isActive: Boolean
) : Parcelable {

    override fun toString(): String {
        return description
    }
}