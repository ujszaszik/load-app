package com.udacity.loadapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FileDao {

    @Insert
    fun insertFile(file: FileEntity): Long

    @Query("SELECT * FROM files ORDER BY file_name ASC")
    fun listAllFiles(): LiveData<List<FileEntity>>

    @Query("SELECT COUNT(*) FROM files WHERE file_name == :fileName")
    fun getFileAmountStoredWithThisName(fileName: String): Int
}