package com.udacity.loadapp.download.writer.module

import com.udacity.loadapp.download.writer.ExternalFileWriter
import com.udacity.loadapp.download.writer.FileWriter
import com.udacity.loadapp.download.writer.MediaStoreFileWriter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class FileWriterModule {

    @Binds
    @MediaStoreWriter
    abstract fun bindMediaStoreFileWriter(mediaStoreFileWriter: MediaStoreFileWriter): FileWriter

    @Binds
    @ExternalWriter
    abstract fun bindExternalFileWriter(externalFileWriter: ExternalFileWriter): FileWriter
}