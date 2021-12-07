
fun main() {

    fun part1(input: List<String>): Int {
        val positions = input[0].split(",").map { it.toInt() }.sorted()
        var minFuel = Int.MAX_VALUE
        for (i in positions[0]..positions[positions.size-1]) {
            //val fuel = positions.fold(0) {sum, acc -> sum + kotlin.math.abs(acc - i) }
            val fuel = positions.fold(0) {sum, ele -> sum + triangle(kotlin.math.abs(ele - i))}
            //println(fuel)
            if (fuel < minFuel) {
                minFuel = fuel
                //println(i)
            }
        }
        //println(positions.size)
        return minFuel
    }

    val test_data = readInput("test_data/day7")
    println(part1(test_data))

    val real_data = readInput("input_data/day7")
    println(part1(real_data))



}

fun triangle(n: Int): Int {
    return n * (n + 1) / 2
}