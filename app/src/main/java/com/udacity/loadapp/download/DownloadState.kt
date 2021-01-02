package com.udacity.loadapp.download

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class DownloadState : Parcelable {

    @Parcelize
    object Loading : DownloadState()

    @Parcelize
    object Success : DownloadState()

    @Parcelize
    object Error : DownloadState()

    @Parcelize
    object Disconnected : DownloadState()

    @Parcelize
    object AlreadyDownloaded : DownloadState()

    @Parcelize
    object NonExisting : DownloadState()

    fun isSuccess(): Boolean = this == Success

    fun isError(): Boolean = this == Error

    fun isFinished(): Boolean = isSuccess() || isError()
}