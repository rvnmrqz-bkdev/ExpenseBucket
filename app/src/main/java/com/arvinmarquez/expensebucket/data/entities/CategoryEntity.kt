package com.arvinmarquez.expensebucket.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "category_table")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "operator", defaultValue = "-")
    val operator : String,

    @ColumnInfo(name = "is_active", defaultValue = "1")
    val isActive : Int

) : Parcelable {
    override fun toString(): String {
        return description
    }
}