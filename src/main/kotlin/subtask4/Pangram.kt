package subtask4

import subtask4.Pangram.Companion.consonants
import subtask4.Pangram.Companion.vowels
import java.util.*
import java.util.regex.Pattern

class Pangram {

    companion object {
        val vowels = listOf('a', 'e', 'i', 'o', 'u', 'y')
        val consonants = listOf(
            'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'
        )
    }

    fun getResult(inputString: String): String {
        if (inputString.trim().isEmpty()) {
            return ""
        }

        var isPangram = true

        ('a'..'z').forEach { letter ->
            if (letter !in inputString.toLowerCase()) {
                isPangram = false
            }
        }

        return if (!isPangram) {
            convertSimpleText(inputString)
        } else {
            convertPangram(inputString)
        }
    }

    private fun convertPangram(inputString: String): String {
        val tempWordList = inputString.trim().split(Pattern.compile("[ \n\t\r]+"))
        val wordsList: MutableList<Word> = mutableListOf()

        tempWordList.forEachIndexed { index, word ->
            var vowelsCounter: Int = 0
            word.toCharArray().toList().forEach {
                if (vowels.contains(it) || vowels.contains(it.toLowerCase())) {
                    vowelsCounter++
                }
            }

            wordsList.add(Word(index, vowelsCounter, word))
        }

        Collections.sort(wordsList, WordComparatorByQuantityAscending())
        var final: String = ""

        val maxIndex = wordsList.lastIndex
        wordsList.forEachIndexed { index, word ->
            when {
                index == 0 -> final = word.getWordByVowels()
                index <= maxIndex -> final = "$final ${word.getWordByVowels()}"
                else -> final += word.getWordByVowels()
            }
        }

        return final
    }

    private fun convertSimpleText(inputString: String): String {
        val tempWordList = inputString.trim().split(Pattern.compile("[ \n\t\r]+"))
        val wordsList: MutableList<Word> = mutableListOf()


        tempWordList.forEachIndexed { index, word ->
            var consonantsCounter: Int = 0
            word.toCharArray().toList().forEach {
                if (consonants.contains(it) || consonants.contains(it.toLowerCase())) {
                    consonantsCounter++
                }
            }

            wordsList.add(Word(index, consonantsCounter, word))
        }

        Collections.sort(wordsList, WordComparatorByQuantityAscending())
        var final: String = ""

        val maxIndex = wordsList.lastIndex
        wordsList.forEachIndexed { index, word ->
            when {
                index == 0 -> final = word.getWordByconsonants()
                index <= maxIndex -> final = "$final ${word.getWordByconsonants()}"
                else -> final += word.getWordByconsonants()
            }
        }

        return final
    }
}

class Word(var index: Int, var vowelQuantity: Int, var text: String) {
    fun getWordByVowels(): String {
        vowels.forEachIndexed { index, c ->
            text = text.replace(c, c.toUpperCase())
        }
        return "$vowelQuantity${text}"
    }

    fun getWordByconsonants(): String {
        consonants.forEachIndexed { index, c ->
            text = text.replace(c, c.toUpperCase())
        }
        return "$vowelQuantity${text}"
    }
}

class WordComparatorByQuantityAscending : Comparator<Word> {
    override fun compare(o1: Word?, o2: Word?): Int {
        if (o1 != null && o2 != null) {
            when {
                o1.vowelQuantity > o2.vowelQuantity -> return 1
                o1.vowelQuantity < o2.vowelQuantity -> return -1
                o1.vowelQuantity == o2.vowelQuantity -> {
                    return if (o1.index > o2.index) 1 else -1
                }
            }
        }

        return 0
    }
}
