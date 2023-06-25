package victor.paez.util

import java.util.Date
import java.util.TimeZone

fun getCalendarTime(time: Long): Date {
    val timeZone = TimeZone.getDefault()
    val difference = timeZone.rawOffset.toLong()
    return Date(time - difference)
}
