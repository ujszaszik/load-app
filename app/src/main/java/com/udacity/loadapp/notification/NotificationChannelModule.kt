package com.udacity.loadapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import com.udacity.loadapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NotificationChannelModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideNotificationChannel(channelInfo: ChannelInfo): NotificationChannel {
        return NotificationChannel(
            channelInfo.id,
            channelInfo.name,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = channelInfo.description
            setShowBadge(true)
        }
    }

    @Provides
    @Singleton
    fun provideChannelInfo(@ApplicationContext context: Context): ChannelInfo {
        return ChannelInfo(
            context.getString(R.string.notification_channel_id),
            context.getString(R.string.notification_channel_name),
            context.getString(R.string.notification_channel_description)
        )
    }
}