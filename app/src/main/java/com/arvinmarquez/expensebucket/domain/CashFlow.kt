package com.arvinmarquez.expensebucket.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class CashFlow(
    val id: Long,
    val description: String,
    var category: Category?,
    val amount: Double,
    val date: Date
) : Parcelable