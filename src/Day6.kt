import java.math.BigInteger

fun main() {
    val DAYS = 256

    fun part1(input: List<String>): BigInteger {
        val fish = mutableMapOf<Int, BigInteger>()
        for (item in input[0].split(",")) {
            fish[item.toInt()] = fish[item.toInt()]?.plus(BigInteger.valueOf(1)) ?: BigInteger.ONE
        }

        for (i in 1 .. DAYS) {
            val temp = fish[0] ?: BigInteger.ZERO
            for (j in 1..8) {
                fish[j - 1] = fish[j] ?: BigInteger.ZERO
            }
            fish[6] = fish[6]?.plus(temp) ?: temp
            fish[8] = temp
            println(fish)
        }

        return fish.values.fold(BigInteger.ZERO) { sum, element -> sum + element}
    }
    val real_data = readInput("input_data/day6")

    println(part1(real_data))
}