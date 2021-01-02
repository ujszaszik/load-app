package com.udacity.loadapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.udacity.loadapp.detail.DetailActivity
import com.udacity.loadapp.network.FileModel
import com.udacity.loadapp.R
import com.udacity.loadapp.download.DownloadState
import com.udacity.loadapp.notification.icon.ErrorBitmap
import com.udacity.loadapp.notification.icon.SuccessBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DownloadNotificator @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManager: NotificationManager,
    private val notificationChannel: NotificationChannel,
    private val notificationBuilder: NotificationCompat.Builder
) {

    @Inject
    @SuccessBitmap
    lateinit var successBitmap: Bitmap

    @Inject
    @ErrorBitmap
    lateinit var errorBitmap: Bitmap

    fun sendNotification(file: FileModel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val builder = notificationBuilder.apply {
            setDownloadDetailsText(file)
            addActionToOpenDetails(file)
            setSmallStatusIcon(file.status)
            setLargeStatusIcon(file.status)
        }

        val notificationId = context.getString(R.string.notification_id)
        NotificationManagerCompat.from(context).notify(notificationId.toInt(), builder.build())
    }

    private fun NotificationCompat.Builder.addActionToOpenDetails(file: FileModel) {
        addAction(
            R.drawable.ic_launcher_background,
            context.getString(R.string.notification_action_label),
            getPendingIntentToOpenDetails(file)
        )
    }

    private fun NotificationCompat.Builder.setDownloadDetailsText(file: FileModel) {
        if (file.status.isSuccess()) setBigText(R.string.notification_success_message, file.name)
        else if (file.status.isError()) setBigText(R.string.notification_error_message, file.name)
    }

    private fun NotificationCompat.Builder.setBigText(textId: Int, param: String) {
        setStyle(NotificationCompat.BigTextStyle().bigText(context.getString(textId, param)))
    }

    private fun NotificationCompat.Builder.setSmallStatusIcon(state: DownloadState) {
        if (state.isSuccess()) setSmallIcon(R.drawable.ic_success_white)
        else if (state.isError()) setSmallIcon(R.drawable.ic_error_white)
    }

    private fun NotificationCompat.Builder.setLargeStatusIcon(state: DownloadState) {
        if (state.isSuccess()) setLargeIcon(successBitmap)
        else if (state.isError()) setLargeIcon(errorBitmap)
    }

    private fun getPendingIntentToOpenDetails(file: FileModel): PendingIntent {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(context.getString(R.string.file_model_intent_id), file)
        }
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}