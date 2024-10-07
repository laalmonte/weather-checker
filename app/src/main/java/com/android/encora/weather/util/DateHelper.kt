package com.android.encora.weather.util
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateHelper {

    val isDayTime : Boolean
        get() {
            val cal = Calendar.getInstance()
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            return hour in 8..20
        }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    public fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}