package com.udacity.loadapp.fetcher.reposelector

import java.math.RoundingMode
import java.text.DecimalFormat

object FileSizeConverter {

    private val decimalFormat = DecimalFormat("#.##").apply {
        roundingMode = RoundingMode.CEILING
    }

    fun convert(sizeInBytes: Long): String {
        return when {
            FileSizeUnit.GB.isSizeOf(sizeInBytes) -> {
                generateStringValue(sizeInBytes, FileSizeUnit.GB)
            }
            FileSizeUnit.MB.isSizeOf(sizeInBytes) -> {
                generateStringValue(sizeInBytes, FileSizeUnit.MB)
            }
            FileSizeUnit.KB.isSizeOf(sizeInBytes) -> {
                generateStringValue(sizeInBytes, FileSizeUnit.KB)
            }
            else -> {
                generateStringValue(sizeInBytes, FileSizeUnit.B)
            }
        }
    }

    private fun generateStringValue(sizeInBytes: Long, fileSizeUnit: FileSizeUnit): String {
        val wholePart = sizeInBytes / fileSizeUnit.conversionRate
        val remainder = sizeInBytes % fileSizeUnit.conversionRate
        val decimalPart = remainder.ratioOf(fileSizeUnit.conversionRate)
        val result = wholePart + decimalPart
        return "${decimalFormat.format(result)} ${fileSizeUnit.textual}"
    }

    private fun Number.ratioOf(other: Number): Double {
        return this.toDouble().div(other.toDouble())
    }
}

private enum class FileSizeUnit(
    val conversionRate: Int,
    val textual: String
) {
    B(1, "B"),
    KB(1024, "KB"),
    MB(1048576, "MB"),
    GB(1073741824, "GB");

    fun isSizeOf(sizeInBytes: Long): Boolean {
        return sizeInBytes.div(this.conversionRate) >= 1
    }

}