package com.enrech.tarisearch.common_data.room.utils

import androidx.room.TypeConverter
import com.enrech.tarisearch.common_domain.extension.now
import kotlinx.datetime.LocalDateTime

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime =
        value?.let { LocalDateTime.parse(value) } ?: LocalDateTime.now()

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?) = date?.toString()
}