package com.udacity.loadapp.notification.icon

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EmptyBitmap

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SuccessBitmap

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ErrorBitmap

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SuccessDrawable

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ErrorDrawable

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IconHeight

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IconWidth