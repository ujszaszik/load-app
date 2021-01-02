package com.udacity.loadapp.fetcher.reposelector

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.udacity.loadapp.R
import com.udacity.loadapp.database.datetime.asFormattedString
import com.udacity.loadapp.download.DownloadState
import com.udacity.loadapp.fetcher.fancybutton.getDrawable
import org.threeten.bp.LocalDateTime

@BindingAdapter("downloadStatus")
fun TextView.downloadStatus(downloadState: DownloadState) {
    if (downloadState.isSuccess()) text = context.getString(R.string.message_successful)
    else if (downloadState.isError()) text = context.getString(R.string.message_successful)
}

@BindingAdapter("statusIcon")
fun ImageView.statusIcon(downloadState: DownloadState) {
    if (downloadState.isSuccess()) setImageDrawable(getDrawable(R.drawable.success_circle))
    else if (downloadState.isError()) setImageDrawable(getDrawable(R.drawable.error_circle))
}

@BindingAdapter("lastModified")
fun TextView.lastModified(time: LocalDateTime?) {
    time?.let {
        text = time.asFormattedString()
    }
}

@BindingAdapter("fileSize")
fun TextView.fileSize(sizeInBytes: Long) {
    text = FileSizeConverter.convert(sizeInBytes)
}