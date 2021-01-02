package com.udacity.loadapp.download.writer

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.Q)
class MediaStoreFileWriter @Inject constructor(
    private val contentResolver: ContentResolver
) : FileWriter {


    companion object {
        private const val ZIP_FILE_EXTENSION = ".zip"
        private const val ZIP_FILE_MIME_TYPE = "application/zip"
        private const val DESTINATION_FOLDER = "/LoadApp/repos/"
        private const val EXTERNAL_CONTENT_URI = "external"
    }

    override fun write(inputStream: InputStream, fileName: String): Long {
        val values = getContentValues(fileName)

        val uri: Uri? = contentResolver.insert(
            MediaStore.Files.getContentUri(EXTERNAL_CONTENT_URI),
            values
        )

        val outputStream: OutputStream? = contentResolver.openOutputStream(uri!!)
        return inputStream.copyTo(outputStream!!)
    }

    private fun getContentValues(fileName: String): ContentValues {
        return ContentValues().apply {
            addDisplayName(fileName + ZIP_FILE_EXTENSION)
            addMimeType(ZIP_FILE_MIME_TYPE)
            addRelativePath(Environment.DIRECTORY_DOWNLOADS + DESTINATION_FOLDER)
        }
    }

    private fun ContentValues.addDisplayName(displayName: String) {
        put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
    }

    private fun ContentValues.addMimeType(mimeType: String) {
        put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
    }

    private fun ContentValues.addRelativePath(path: String) {
        put(MediaStore.MediaColumns.RELATIVE_PATH, path)
    }

}