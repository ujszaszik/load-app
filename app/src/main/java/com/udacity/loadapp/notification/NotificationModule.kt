package com.udacity.loadapp.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.udacity.loadapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NotificationModule {

    @Provides
    @Singleton
    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context,
        channelInfo: ChannelInfo
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelInfo.id)
            .setContentTitle(context.getString(R.string.notification_content_title))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
    }

}