import java.awt.Point
import java.lang.Integer.max
import java.lang.Integer.min
import java.util.stream.Collectors

fun main() {

    fun part1(input: List<String>): Int {
        val lines = input.stream().map { toLine(it) }.collect(Collectors.toList())
        val oceanMap = mutableMapOf<Point, Int>()
        for (line in lines) {
            //if (line.isFlat()) {
                for (point in line.getPoints()) {
                    oceanMap[point] = oceanMap[point]?.plus(1) ?: 1
                }
            //}
        }
        return oceanMap.filter { it.value > 1 }.size
    }
    val realInput = readInput("input_data/day5")
    val testInput = readInput("test_data/day5")
    println(part1(testInput))
    println(part1(realInput))
    //println(Line(Point(6,4), Point(2,0)).getPoints())

}

fun toLine(input: String): Line {
    val points = input.split(" -> ")
    return Line(toPoint(points[0]), toPoint(points[1]))
}

fun toPoint(input: String): Point {
    val coordinates = input.split(",")
    return Point(coordinates[0].toInt(), coordinates[1].toInt())
}

class Line(val p1: Point, val p2: Point) {
    fun getPoints(): List<Point> {
        if (p1 == p2) return listOf(p1)
        val points = mutableListOf<Point>()
        val deltaX = p1.x - p2.x
        if (deltaX == 0) {
            for (i in min(p1.y, p2.y)..max(p1.y, p2.y)) {
                points.add(Point(p1.x, i))
            }
            return points
        }
        val deltaY = p1.y - p2.y
        if (deltaY == 0) {
            for (i in min(p1.x, p2.x)..max(p1.x, p2.x)) {
                points.add(Point(i, p1.y))
            }
            return points
        }
        val slope = deltaY / deltaX
        val leftPoint = if (p1.x < p2.x) p1 else p2
        if (slope > 0) {
            for (i in 0..kotlin.math.abs(p1.x - p2.x)) {
                points.add(Point(leftPoint.x + i, leftPoint.y + i))
            }
        } else { // slope == -1
            for (i in 0..kotlin.math.abs(p1.x - p2.x)) {
                points.add(Point(leftPoint.x + i, leftPoint.y - i))
            }
        }
        return points
    }

    fun isFlat(): Boolean {
        return (p1.x == p2.x) or (p1.y == p2.y)
    }
}
