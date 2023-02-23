package year2022.day04

import java.io.File

class Day04(text : String = "input.txt") {

    private val day = "04"
    private val input = File("src/resources/2022/$day/$text").readLines().asSequence().map { s ->
        s.split(",").map { r ->
        val (a , b) = r.split("-").map { it.toInt() }
        a..b
    } }
    private operator fun IntRange.contains(i: IntRange): Boolean = this.first <= i.first && this.last >= i.last
    fun part1(): Int = input.count{ (a, b) -> b in a || a in b }
    fun part2(): Int = input.count{ (a, b) -> a.first in b || b.first in a }
}
fun main() {
    val day = Day04()
    println(day.part1())
    println(day.part2())
}

