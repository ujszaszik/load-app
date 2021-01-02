package com.udacity.loadapp.notification.icon

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.udacity.loadapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class IconModule {

    @Provides
    @SuccessBitmap
    fun provideSuccessBitmap(
        @EmptyBitmap bitmap: Bitmap,
        @SuccessDrawable successDrawable: Drawable
    ): Bitmap = successDrawable.toDrawnBitmap(bitmap)

    @Provides
    @ErrorBitmap
    fun provideErrorBitmap(
        @EmptyBitmap bitmap: Bitmap,
        @ErrorDrawable errorDrawable: Drawable
    ): Bitmap = errorDrawable.toDrawnBitmap(bitmap)

    @Provides
    @EmptyBitmap
    fun provideEmptyBitmap(@IconHeight height: Float, @IconWidth width: Float): Bitmap {
        return Bitmap.createBitmap(
            width.toInt(),
            height.toInt(),
            Bitmap.Config.ARGB_8888
        )
    }

    @Provides
    @SuccessDrawable
    fun provideSuccessDrawable(@ApplicationContext context: Context): Drawable {
        return ContextCompat.getDrawable(context, R.drawable.ic_success_green)!!
    }

    @Provides
    @ErrorDrawable
    fun provideErrorDrawable(@ApplicationContext context: Context): Drawable {
        return ContextCompat.getDrawable(context, R.drawable.ic_error_red)!!
    }

    @Provides
    @IconHeight
    fun provideIconHeight(@ApplicationContext context: Context): Float {
        return context.resources.getDimension(android.R.dimen.notification_large_icon_height)
    }

    @Provides
    @IconWidth
    fun provideIconWidth(@ApplicationContext context: Context): Float {
        return context.resources.getDimension(android.R.dimen.notification_large_icon_width)
    }

}