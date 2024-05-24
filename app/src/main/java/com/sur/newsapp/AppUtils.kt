package com.sur.newsapp

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class AppUtils {
    companion object{

        fun convertDateTimeToSpecifiedDateTime(
            dateStr: String?,
            srcFormat: String,
            targetFormat: String
        ): String? {
            return try {
                val sourceFormat = SimpleDateFormat(srcFormat, Locale.US)
                val destFormat = SimpleDateFormat(targetFormat, Locale.US)
                sourceFormat.timeZone = TimeZone.getDefault()
                destFormat.timeZone = TimeZone.getDefault()
                val convertedDate: Date = sourceFormat.parse(dateStr)
                destFormat.format(convertedDate)
            } catch (e: Exception) {
                ""
            }
        }
    }
}