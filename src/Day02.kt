

fun main() {

    fun part1(input: List<String>): Int {
        var curPos = Position(0,0)
        for (item in input) {
            //println(item)
            curPos += parse_direction(item)
        }
        //println(curPos)
        return curPos.x * curPos.y
    }

    fun part2(input: List<String>): Int {
        var aim = 0
        var cur_pos = Position(0,0)
        for (item in input) {
            val split_str = item.split(" ")
            when (split_str[0]) {
                Direction.FORWARD.str -> cur_pos += Position(split_str[1].toInt(), aim * split_str[1].toInt())
                Direction.UP.str -> aim -= split_str[1].toInt()
                Direction.DOWN.str -> aim += split_str[1].toInt()
            }
        }
        println(cur_pos)
        return cur_pos.x * cur_pos.y
    }
    val testInput = readInput("test_data/day2")
    println(part2(testInput))
    check(part2(testInput) == 1932)

    val realInput = readInput("input_data/day2")
    println(part2(realInput))

}

class Position(val x: Int, val y: Int) {
    operator fun plus(other: Position): Position {
        return Position(this.x + other.x, this.y + other.y)
    }

    override fun toString(): String {
        return "x: $x, y: $y"
    }
}

enum class Direction(val str: String) {
    FORWARD("forward"), DOWN("down"), UP("up")
}

fun parse_direction(direction: String): Position {
    val split_str = direction.split(" ")
    return when (split_str[0]) {
        Direction.FORWARD.str -> Position(split_str[1].toInt(), 0)
        Direction.DOWN.str -> Position(0, split_str[1].toInt())
        Direction.UP.str -> Position(0, -1 * split_str[1].toInt())
        else -> Position(0,0)
    }
}