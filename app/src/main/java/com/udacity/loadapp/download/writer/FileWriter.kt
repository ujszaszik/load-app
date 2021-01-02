package com.udacity.loadapp.download.writer

import java.io.InputStream

interface FileWriter {

    fun write(inputStream: InputStream, fileName: String): Long
}