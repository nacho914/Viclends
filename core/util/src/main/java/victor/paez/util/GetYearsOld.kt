package victor.paez.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun getYearsOld(birthday: Date?): Int {
    val currentDate = Calendar.getInstance().time
    val format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())

    val birthDateString = format.format(birthday)
    val currentDateStr = format.format(currentDate)

    val birthYear = birthDateString.substring(0, 4).toInt()
    val birthMonth = birthDateString.substring(4, 6).toInt()
    val birthDay = birthDateString.substring(6, 8).toInt()

    val currentYear = currentDateStr.substring(0, 4).toInt()
    val currentMonth = currentDateStr.substring(4, 6).toInt()
    val currentDay = currentDateStr.substring(6, 8).toInt()

    var age = currentYear - birthYear

    if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
        age--
    }

    return age
}
