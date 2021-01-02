package com.udacity.loadapp.database

import androidx.room.TypeConverter
import com.udacity.loadapp.download.DownloadState


class DownloadStateTypeConverter {

    companion object {

        private const val SUCCESS = "Success"
        private const val ERROR = "Error"


        @JvmStatic
        @TypeConverter
        fun convertToState(text: String): DownloadState? {
            return when (text) {
                SUCCESS -> DownloadState.Success
                ERROR -> DownloadState.Error
                else -> null
            }
        }

        @JvmStatic
        @TypeConverter
        fun convertToString(state: DownloadState): String {
            return when (state) {
                DownloadState.Success -> SUCCESS
                DownloadState.Error -> ERROR
                else -> ""
            }
        }
    }
}