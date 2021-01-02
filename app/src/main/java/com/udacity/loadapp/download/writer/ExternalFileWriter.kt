package com.udacity.loadapp.download.writer

import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class ExternalFileWriter @Inject constructor() : FileWriter {

    companion object {
        private const val ZIP_FILE_EXTENSION = ".zip"
    }

    @Suppress("DEPRECATION")
    private val storageDirectory =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    override fun write(inputStream: InputStream, fileName: String): Long {
        val path = "$storageDirectory/$fileName$ZIP_FILE_EXTENSION"
        File(path).run {
            createNewFile()
            FileOutputStream(this).use { output ->
                return inputStream.copyTo(output)
            }
        }
    }
}