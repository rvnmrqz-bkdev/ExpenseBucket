package com.arvinmarquez.expensebucket.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long,

    @ColumnInfo(name = "display_name")
    val displayName : String

) {
}