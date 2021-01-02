package com.udacity.loadapp.network

import android.os.Parcelable
import com.udacity.loadapp.download.DownloadState
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class FileModel(
    val name: String = "",
    val size: Long = 0,
    val status: DownloadState = DownloadState.NonExisting,
    val lastModified: LocalDateTime = LocalDateTime.now()
) : Parcelable