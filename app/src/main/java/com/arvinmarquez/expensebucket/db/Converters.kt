package com.arvinmarquez.expensebucket.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.arvinmarquez.expensebucket.data.entities.CategoryEntity
import com.google.gson.Gson
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun categoryEntityToJson(value: CategoryEntity) = Gson().toJson(value)

    @TypeConverters
    fun categoryJsonToEntity(value: String) = Gson().fromJson(value, CategoryEntity::class.java)

}