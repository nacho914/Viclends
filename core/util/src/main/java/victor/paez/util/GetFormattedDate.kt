package victor.paez.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getFormattedDate(date: Date?): String {
    if (date == null) {
        return ""
    }

    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}
