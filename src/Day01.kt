import java.util.stream.Collectors

fun main() {
    fun part1(input: List<String>): Int {
        val asInts = input.stream().map {it.toInt()}
        var soln = 0
        val itr = asInts.iterator()
        var previous = Int.MAX_VALUE
        while(itr.hasNext()) {
            val cur = itr.next()
            if (cur > previous) soln++
            previous = cur
        }
        return soln
    }

    fun part2(input: List<String>): Int {
        val asInts = input.stream().map { it.toInt() }.collect(Collectors.toList())
        var soln = 0
        for (i in 0..asInts.size - 4) {
            if (asInts[i] < asInts[i + 3]) {
                soln++
            }
        }
        return soln
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day1_1_test")
    println(part2(testInput))
    check(part2(testInput) == 5)

    val input = readInput("day1_1")
    println(part1(input))
    println(part2(input))
}
