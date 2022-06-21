package com.arvinmarquez.expensebucket.data.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(
    tableName = "expense_table",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExpenseEntity(

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
) : Parcelable