import kotlin.time.measureTimedValue

object TestRunner {

    fun run(name: String, times: Int, block: () -> Any) {
        var result: Any? = null
        var totalMillis = 0L
        repeat(times) {
            measureTimedValue {
                block()
            }.let { timedValue ->
                result = timedValue.value
                totalMillis += timedValue.duration.inWholeMilliseconds
            }
        }
        println(
            "$name run $times times with result = $result, " +
                    "total execution time = ${totalMillis}ms, " +
                    "average execution time = ${totalMillis / times.toFloat()}ms"
        )
    }
}