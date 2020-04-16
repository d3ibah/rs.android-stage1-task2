package subtask5

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass

class Blocks {

    fun getData(blockA: Array<Any>, blockB: KClass<*>): Any {
        return when (blockB) {
            Int::class -> blockA.filterIsInstance<Int>().sum()
            String::class -> blockA.filterIsInstance<String>().joinToString("")
            LocalDate::class -> {
                blockA.filterIsInstance<LocalDate>().max()?.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    ?: ""
            }
            else -> "Wrong argument"
        }
    }
}
