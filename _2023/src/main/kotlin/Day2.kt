import kotlin.math.max

object Day2 {

    fun part1(input: List<String>?, testData: Map<String, Int>): Int =
        input?.filter { game ->
            game.substringAfter(": ").split(";").all { draws ->
                draws.split(",").all { draw ->
                    val number = draw.trim().substringBefore(" ").toInt()
                    val color = draw.trim().substringAfter(" ")
                    (testData[color] ?: 0) >= number
                }
            }
        }?.sumOf { line -> line.gameNumber() } ?: 0

    fun part2(input: List<String>?): Int = input?.sumOf { game ->
        val rgb = intArrayOf(0, 0, 0)
        game.substringAfter(": ").split(";").map { draws ->
            draws.split(",").forEach { draw ->
                val number = draw.trim().substringBefore(" ").toInt()
                when (draw.trim().substringAfter(" ")) {
                    "red"   -> rgb[0] = max(rgb[0], number)
                    "green" -> rgb[1] = max(rgb[1], number)
                    "blue"  -> rgb[2] = max(rgb[2], number)
                }
            }
        }
        rgb.product()
    } ?: 0

    private fun String.gameNumber(): Int = this.substringAfter("Game ").substringBefore(":").toInt()

    private fun IntArray.product(): Int = this.reduce { acc, i -> acc * i }
}

enum class Color {
    RED, GREEN, BLUE
}

fun main() {
    val input = Day2::class.java.getResourceAsStream("day2")?.bufferedReader()?.readLines()
    val testData = mapOf("red" to 12, "green" to 13, "blue" to 14)
    TestRunner.run("Day2:part1", 1000) { Day2.part1(input, testData) }
    TestRunner.run("Day2:part2", 1000) { Day2.part2(input) }
}