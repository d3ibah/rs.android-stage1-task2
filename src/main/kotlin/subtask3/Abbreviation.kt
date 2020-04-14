package subtask3

class Abbreviation {

    fun abbreviationFromA(a: String, b: String): String {

        if (a.length < b.length) {
            return "NO"
        }

        var tempA = a
        tempA = tempA.toUpperCase()
        val aCharList = tempA.toCharArray().toList()
        val aMap : MutableMap<Int, Char> = mutableMapOf()
        aCharList.forEachIndexed{ index, char ->
            aMap[index] = char
        }
        var previousIndex = -1
        val bCharList = b.toCharArray().toList()
        bCharList.forEachIndexed { index, char ->
            val tempHashA = aMap.filter { it.value == char }
            if (tempHashA.isEmpty()) {
                return "NO"
            } else {
                val currentIndex = tempHashA.keys.min() ?: -1
                if (currentIndex <= previousIndex) {
                    return "NO"
                } else {
                    aMap.remove(currentIndex)
                    previousIndex = currentIndex
                }
            }
        }

        return "YES"
    }
}
