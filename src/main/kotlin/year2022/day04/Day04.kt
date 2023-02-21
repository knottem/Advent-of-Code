package year2022.day04

import java.io.File

class Day04(text : String = "input.txt") {

    private val day = "04"

    val input = File("src/main/resources/2022/$day/$text").readLines().toList()

    fun part1(): Int {
        return 0
    }

    fun part2(): Int {
        return 0
    }

}

fun main() {
    val day = Day04("test.txt")
    day.input.forEach(::println)
}

