package com.udacity.loadapp.database.datetime

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)

fun LocalDateTime.asFormattedString(): String = dateFormatter.format(this)

fun String.asLocalDate(): LocalDateTime = LocalDateTime.parse(this, dateFormatter)