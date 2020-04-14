package subtask1

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    fun toTextDay(day: String, month: String, year: String): String {
        val calendar = Calendar.getInstance()
        calendar.isLenient = false
        val dateFormat = SimpleDateFormat("d MMMM, EEEE", Locale("ru"))

        return try {
            calendar.set(year.toInt(), (month.toInt() - 1), day.toInt())
            dateFormat.format(calendar.timeInMillis)
        } catch (e: Exception) {
            "Такого дня не существует"
        }
    }
}
