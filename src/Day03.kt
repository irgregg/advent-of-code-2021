

fun main() {

    fun part1(input: List<String>): Int {

        val ct = IntArray(input[0].length)
        for (item in input) {
            for (i in item.indices) {
                //println(item[i].code)
                ct[i] += (item[i].code - 48)
            }
        }
        //println(ct.asList())
        //println(input.size)
        val gamma = IntArray(input[0].length)
        val epsilon = IntArray(input[0].length)
        for (i in ct.indices) {
            if (ct[i] * 2 > input.size) {
                gamma[i] = 1
            } else {
                epsilon[i] = 1
            }
        }
        //println(gamma.asList())
        //println(epsilon.asList())
        val gamma_val = intArrToInt(gamma)
        val eps_val = intArrToInt(epsilon)

        return gamma_val * eps_val
    }

    fun part2(input: List<String>): Int {
        var oxygenVals = input.toList()
        var co2Vals = input.toList()

        var o2ctr = 0
        while (oxygenVals.size > 1) {
            val mostCommon = mostCommon(oxygenVals, o2ctr)
            oxygenVals = oxygenVals.filter { it[o2ctr] == mostCommon }
            o2ctr++
        }
        val o2 = strToInt(oxygenVals[0])
        //println(o2)

        var co2ctr = 0
        while (co2Vals.size > 1) {
            val mostCommon = mostCommon(co2Vals, co2ctr)
            co2Vals = co2Vals.filterNot {it[co2ctr] == mostCommon }
            //println(co2Vals)
            co2ctr++
        }
        val co2 = strToInt(co2Vals[0])
        //println(co2)

        return co2 * o2
    }

    val test_input = readInput("test_data/day3")
    //println(part1(test_input))
    check(part1(test_input) == 4191656)
    println(part2(test_input))
    check(part2(test_input) == 8114081)

    val real_input = readInput("input_data/day3")
    println(part1(real_input))
    println(part2(real_input))
}

fun intArrToInt(binaryVal: IntArray): Int {
    var binVal = 0
    for (i in binaryVal.indices) {
        binVal = binVal * 2 + binaryVal[i]
    }
    return binVal
}

fun mostCommon(vals: List<String>, ptr: Int): Char {
    val ct = mutableMapOf('0' to 0, '1' to 0)
    for (item in vals) {
        ct[item[ptr]] = ct[item[ptr]]!!.plus(1)
    }
    return if (ct['0']!! > ct['1']!!) {
        '0'
    } else {
        '1'
    }
}

fun strToInt(binaryVal: String): Int {
    var binVal = 0
    for (i in binaryVal.indices) {
        binVal = binVal * 2 + (binaryVal[i].code - 48)
    }
    return binVal
}