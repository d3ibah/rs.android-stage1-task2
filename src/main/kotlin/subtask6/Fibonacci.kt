package subtask6

class Fibonacci {

    fun productFibonacciSequenceFor(n: Int): IntArray {
        var startNumber = 0
        var nextNumber = 1
        var fibNumber = 1

        while (startNumber * nextNumber < n) {
            fibNumber = startNumber + nextNumber
            startNumber = nextNumber
            nextNumber = fibNumber
        }

        return if (startNumber * nextNumber == n) {
            intArrayOf(startNumber, nextNumber, 1)
        } else {
            intArrayOf(startNumber, nextNumber, 0)
        }
    }
}
