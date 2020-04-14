package subtask2

class TimeConverter {

    fun toTextFormat(hour: String, minute: String): String {
        var intHour = -1
        var intMinute = -1

        try {
            intHour = hour.toInt()
            intMinute = minute.toInt()
        } catch (e: Exception) {
            throw IllegalArgumentException("Arguments parsing are impossible")
        }

        return ClockFormat.getByMinute(intHour, intMinute)
    }

    enum class ClockFormat(private val pattern: String) {
        O_CLOCK("%1\$s o' clock"),
        ONE_PAST("%1\$s minute past %2\$s"),
        FEW_PAST("%1\$s minutes past %2\$s"),
        QUARTER_PAST("quarter past %1\$s"),
        HALF_PAST("half past %1\$s"),
        BEFORE_TO("%1\$s minutes to %2\$s"),
        QUARTER_BEFORE_TO("quarter to %1\$s");

        companion object {
            private val unit = listOf(
                "zero", "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
            )
            private val tenner = listOf(
                "zero", "ten", "twenty", "thirty", "forty",
                "fifty", "sixty", "seventy", "eighty", "ninety"
            )

            fun getByMinute(hour: Int, minute: Int): String {
                return when {
                    (minute == 0) -> String.format(O_CLOCK.pattern, unit[hour])
                    (minute == 1) -> String.format(ONE_PAST.pattern, unit[minute], unit[hour])
                    (minute == 15) -> String.format(QUARTER_PAST.pattern, unit[hour])
                    (minute == 30) -> String.format(HALF_PAST.pattern, unit[hour])
                    (minute == 45) -> String.format(QUARTER_BEFORE_TO.pattern, unit[hour + 1])
                    (minute in 1..29 && minute != 15) -> getTimeBeforeHalf(hour, minute)
                    (minute in 31..59 && minute != 45) -> getTimeAfterHalf(hour, minute)
                    else -> ""
                }
            }

            private fun getTimeBeforeHalf(hour: Int, minute: Int): String {
                return getTemp(FEW_PAST.pattern, hour, minute)
            }

            private fun getTimeAfterHalf(hour: Int, minute: Int): String {
                val nextHour = hour + 1
                val remainsMinutes = 60 - minute
                return getTemp(BEFORE_TO.pattern, nextHour, remainsMinutes)
            }

            private fun getTemp(pattern: String, hour: Int, minute: Int): String {
                var resultMinute = ""
                var resultHour = ""
                when {
                    minute < 20 -> resultMinute = unit[minute]
                    minute == 20 -> resultMinute = tenner[minute / 10]
                    minute > 20 -> resultMinute = "${tenner[minute / 10]} ${unit[minute % 10]}"
                }

                when {
                    hour < 20 -> resultHour = unit[hour]
                    hour == 20 -> resultHour = tenner[hour / 10]
                    hour > 20 -> resultHour = "${tenner[hour / 10]} ${unit[hour % 10]}"
                }

                return String.format(pattern, resultMinute, resultHour)
            }
        }
    }
}
