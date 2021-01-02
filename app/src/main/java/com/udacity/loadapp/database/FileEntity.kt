package com.udacity.loadapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.loadapp.network.FileModel
import com.udacity.loadapp.download.DownloadState
import org.threeten.bp.LocalDateTime

@Entity(tableName = "files")
data class FileEntity(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "file_name") var fileName: String,
    @ColumnInfo(name = "size_in_bytes") var sizeInBytes: Long,
    @ColumnInfo(name = "download_state") var status: DownloadState,
    @ColumnInfo(name = "last_modified") var lastModified: LocalDateTime
) {

    companion object {
        fun create(fileName: String, fileSize: Long, state: DownloadState): FileEntity {
            return FileEntity(0, fileName, fileSize, state, LocalDateTime.now())
        }

    }

    fun toModel() = FileModel(
        fileName,
        sizeInBytes,
        status,
        lastModified
    )
}