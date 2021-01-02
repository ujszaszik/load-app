package com.udacity.loadapp.fetcher.fancybutton

import androidx.databinding.BindingAdapter
import com.udacity.loadapp.download.DownloadState


@BindingAdapter("downloadState")
fun FancyButton.downloadState(downloadState: DownloadState?) {
    when (downloadState) {
        DownloadState.Loading -> startDownload()
        DownloadState.Success -> onDownloadFinished(true)
        DownloadState.Error -> onDownloadFinished(false)
        DownloadState.Disconnected -> onDisconnected()
        DownloadState.NonExisting -> onNonExistingFile()
        DownloadState.AlreadyDownloaded -> onAlreadyDownloaded()
    }
}