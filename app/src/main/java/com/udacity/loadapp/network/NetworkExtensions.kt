package com.udacity.loadapp.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection

suspend fun <T> IO(block: suspend () -> T): T {
    return withContext(Dispatchers.IO) {
        block.invoke()
    }
}

private const val HTTP_SUCCESS_CODE_PREFIX = "2"
fun HttpURLConnection.isValid() = responseCode.toString().startsWith(HTTP_SUCCESS_CODE_PREFIX)