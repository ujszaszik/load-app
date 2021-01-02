package com.udacity.loadapp.download.writer

import android.os.Build
import com.udacity.loadapp.download.writer.module.ExternalWriter
import com.udacity.loadapp.download.writer.module.MediaStoreWriter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileWriterFactory @Inject constructor() {

    @ExternalWriter
    @Inject
    lateinit var externalFileWriter: FileWriter

    @MediaStoreWriter
    @Inject
    lateinit var mediaStoreFileWriter: FileWriter

    fun get(): FileWriter {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            mediaStoreFileWriter
        } else {
            externalFileWriter
        }
    }
}