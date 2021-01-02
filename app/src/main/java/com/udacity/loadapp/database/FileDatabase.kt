package com.udacity.loadapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.udacity.loadapp.database.datetime.LocalDateTimeTypeConverter

@Database(entities = [FileEntity::class], exportSchema = false, version = 2)
@TypeConverters(LocalDateTimeTypeConverter::class, DownloadStateTypeConverter::class)
abstract class FileDatabase : RoomDatabase() {

    abstract fun fileDao(): FileDao

}