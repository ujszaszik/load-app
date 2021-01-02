package com.udacity.loadapp.download.writer.module

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ExternalWriter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MediaStoreWriter