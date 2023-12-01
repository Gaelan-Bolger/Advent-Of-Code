object Day1 {

    fun part1(): Int =
        javaClass.classLoader.getResourceAsStream("day1")
            ?.bufferedReader()?.readLines()?.sumOf { line ->
                if (line.isEmpty()) 0
                else {
                    val firstDigit = line.first { it.isDigit() }
                    val lastDigit = line.lastOrNull { it.isDigit() }
                    "${firstDigit}${lastDigit ?: firstDigit}".toInt()
                }
            } ?: 0

    fun part2(): Int =
        javaClass.classLoader.getResourceAsStream("day1")
            ?.bufferedReader()?.readLines()?.sumOf { line ->
                if (line.isEmpty()) 0
                else {
                    val strings = listOf(
                        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9"
                    )
                    val firstMatch = line.findAnyOf(strings)
                    val firstDigit = if (firstMatch?.second?.toCharArray()?.first()?.isDigit() == true)
                        firstMatch.second.toInt() else firstMatch?.second?.wordToDigit() ?: 0
                    val lastMatch = line.findLastAnyOf(strings)
                    val lastDigit = if (lastMatch?.second?.toCharArray()?.first()?.isDigit() == true)
                        lastMatch.second.toInt() else lastMatch?.second?.wordToDigit()
                    "${firstDigit}${lastDigit ?: firstDigit}".toInt()
                }
            } ?: 0
}

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
    println(Day1.part1())
    println(Day1.part2())
}