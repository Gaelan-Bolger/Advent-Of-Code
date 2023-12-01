import kotlin.time.measureTimedValue

object Day1 {

    fun part1(lines: List<String>): Int = lines.sumOf { line ->
        if (line.isEmpty()) 0
        else {
            val firstDigit = line.first { it.isDigit() }
            val lastDigit = line.lastOrNull { it.isDigit() }
            "${firstDigit}${lastDigit ?: firstDigit}".toInt()
        }
    }

    fun part2(lines: List<String>): Int = lines.sumOf { line ->
        if (line.isEmpty()) 0
        else {
            val firstMatch = line.findAnyOf(targetStrings)
            val firstDigit = firstMatch?.second?.toIntOrNull() ?: firstMatch?.second?.wordToDigit() ?: 0
            val lastMatch = line.findLastAnyOf(targetStrings)
            val lastDigit = lastMatch?.second?.toIntOrNull() ?: lastMatch?.second?.wordToDigit()
            "${firstDigit}${lastDigit ?: firstDigit}".toInt()
        }
    }
}

private val targetStrings = listOf(
    "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
    "1", "2", "3", "4", "5", "6", "7", "8", "9"
)

private fun String?.wordToDigit() = when (this) {
    "one"   -> 1
    "two"   -> 2
    "three" -> 3
    "four"  -> 4
    "five"  -> 5
    "six"   -> 6
    "seven" -> 7
    "eight" -> 8
    "nine"  -> 9
    else    -> null
}

fun main() {
    val lines = Day1::class.java.getResourceAsStream("day1")
        ?.bufferedReader()?.readLines()?.toList() ?: emptyList()
    println(measureTimedValue { Day1.part1(lines) }.let { "Part 1: ${it.value} in ${it.duration.inWholeMilliseconds} ms" })
    println(measureTimedValue { Day1.part2(lines) }.let { "Part 2: ${it.value} in ${it.duration.inWholeMilliseconds} ms" })
}