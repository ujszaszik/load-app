package com.udacity.loadapp.database.datetime

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime

class LocalDateTimeTypeConverter {

    companion object {

        @JvmStatic
        @TypeConverter
        fun toLocalDateTime(dateText: String?): LocalDateTime? = dateText?.asLocalDate()

        @JvmStatic
        @TypeConverter
        fun fromLocalDate(date: LocalDateTime?): String? = date?.asFormattedString()

    }
}