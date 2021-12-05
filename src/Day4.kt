import java.lang.StringBuilder
import java.util.stream.Collectors

fun main() {
    fun part1(data: List<String>): Int {
        val moves = data[0].split(",")
        val boards = loadBoards(data.slice(1 until data.size))
        for (move in moves) {
            for (board in boards) {
                board.add(move.toInt())
                if (board.hasWon()) return board.score() * move.toInt()
            }
        }
        return 0
    }

    fun part2(data: List<String>): Int {
        val moves = data[0].split(",")
        val boards = loadBoards(data.slice(1 until data.size)).toMutableList()
        return findLosingBoard(moves, boards)

    }

    val real_data = readInput("input_data/day4")
    println(part2(real_data))
}

class Board(vals: List<List<Int>>) {
    private val winningRows: MutableList<MutableList<Int>> = mutableListOf()

    init {
        for (row in vals) {
            winningRows.add(row.toMutableList())
        }
        for (i in vals[0].indices) {
            val col = mutableListOf<Int>()
            for (j in vals.indices) {
                col.add(vals[j][i])
            }
            winningRows.add(col)
        }
    }

    fun add(num: Int) {
        for (row in winningRows) {
            if (num in row) row.remove(num)
        }
    }

    fun hasWon(): Boolean {
        return winningRows.any { it.size == 0 }
    }

    fun score(): Int {
        val actualNums = winningRows.slice(0 until 5)
        var score = 0
        for (row in actualNums) {
            score += row.sum()
        }
        return score
    }

    override fun toString(): String {
        val toPrint = StringBuilder()
        for (i in 0 until 5) {
            toPrint.append(winningRows[i])
        }
        return toPrint.toString()
    }
}


fun processLine(line: String): List<Int> {
    //println(line)
    return line.split(" ").stream().filter{it != ""}.map { it.toInt() }.collect(Collectors.toList())
}

// takes in all input value after the first line, inc empty rows
fun loadBoards(input: List<String>): List<Board> {
    val boards = mutableListOf<Board>()
    for (i in input.indices step 6) {
        val lines = mutableListOf<List<Int>>()
        for (j in 1 until 6) {
            lines.add(processLine(input[i + j]))
        }
        boards.add(Board(lines))
    }
    return boards
}

fun findLosingBoard(moves: List<String>, boards: List<Board>): Int {
    var mutBoards = boards.toMutableList()
    var moveCtr = 0
    while (mutBoards.size > 1) {
        for (board in boards) {
            board.add(moves[moveCtr].toInt())
        }
        mutBoards = mutBoards.filterNot { it.hasWon() }.toMutableList()
        moveCtr++
    }
    val losingBoard = mutBoards[0]
    while (!losingBoard.hasWon()) {
        losingBoard.add(moves[moveCtr].toInt())
        if (losingBoard.hasWon()) break
        moveCtr++
    }

    return losingBoard.score() * moves[moveCtr].toInt()
}