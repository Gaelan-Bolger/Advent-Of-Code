object Day3 {

    fun part1(input: List<String>): Int {
        val chunks = input.mapIndexed { index, s ->
            val pad = ".".repeat(s.length)
            when (index) {
                0              -> listOf(pad, s, input[1])
                input.size - 1 -> listOf(input[index - 1], s, pad)
                else           -> listOf(input[index - 1], s, input[index + 1])
            }
        }
        return chunks.sumOf { evalChunk(it) }
    }

    private fun evalChunk(chunk: List<String>): Int {
//        println("abov ${chunk[0]}")
//        println("eval ${chunk[1]}")
//        println("belw ${chunk[2]}")
        var sum = 0
        var index = 0
        val lineToEval = chunk[1]
        while (index < lineToEval.length) {
            val nextDigit = lineToEval.substring(index).indexOfFirst { it.isDigit() }
            if (nextDigit == -1) {
                break
            }

            val digitStart = index + nextDigit
            val digitLength = lineToEval.substring(digitStart).indexOfFirst { !it.isDigit() }
            val digitEnd = if (digitLength == -1) lineToEval.length else digitStart + digitLength

            val digit = lineToEval.substring(digitStart, digitEnd.coerceAtMost(lineToEval.length)).toInt()
//            println("Found digit $digit at index $digitStart, length $digitLength, end $digitEnd")

            if ((digitStart > 0 && lineToEval.charAt(digitStart - 1).isSymbol())
                || (digitEnd < chunk[0].length && lineToEval.charAt(digitEnd).isSymbol())
            ) {
//                println("\tSymbol adjec, adding $digit to sum")
                sum += digit
            } else {
                val startIndex = (digitStart - 1).coerceAtLeast(0)
                val endIndex = (digitEnd + 1).coerceAtMost(chunk[0].length - 1)
                if (chunk[0].substring(startIndex, endIndex).containsSymbol()) {
//                    println("\tSymbol above, adding $digit to sum")
                    sum += digit
                } else if (chunk[2].substring(startIndex, endIndex).containsSymbol()) {
//                    println("\tSymbol below, adding $digit to sum")
                    sum += digit
                }
            }

            index = digitEnd
        }

//        println("sum is $sum")
        return sum
    }

    private fun Char.isSymbol(): Boolean = this != '.' && !isDigit()
    private fun String.containsSymbol(): Boolean = this.any { it.isSymbol() }
    private fun String.charAt(index: Int): Char = if (index < 0 || index >= this.length) '.' else this[index]
}

fun main() {
    val input = Day2::class.java.getResourceAsStream("day3")?.bufferedReader()?.readLines()
    TestRunner.run("Day3:part1", 1000) { Day3.part1(input!!) }
}