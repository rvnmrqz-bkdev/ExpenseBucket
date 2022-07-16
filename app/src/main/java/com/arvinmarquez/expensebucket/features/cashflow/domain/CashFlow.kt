package com.arvinmarquez.expensebucket.features.cashflow.domain

import android.os.Parcelable
import com.arvinmarquez.expensebucket.features.category.domain.Category
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class CashFlow(
    val id: Long,
    val description: String,
    var category: Category?,
    val amount: Double,
    val date: Date
) : Parcelable