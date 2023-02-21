package year2022.day10

import java.io.File


//https://adventofcode.com/2022/day/10
class Day10(text : String = "input.txt") {

    private val input = File("src/main/resources/2022/10/$text").readLines().toList()

    private val set = setOf(20, 60, 100, 140, 180, 220)

    private fun getListOfValues(): MutableList<Int> {
        var value = 1
        val cycle = mutableListOf(value)
        input.forEach {
            cycle.add(value)
            if (it.startsWith("addx")) {
                value += it.replace("addx ", "").toInt()
                cycle.add(value)
            }
        }
        return cycle
    }

    fun part1(): Int = getListOfValues().filterIndexed { index, _ -> index in set.map { it - 1 } }.withIndex().sumOf {  it.value * set.elementAt(it.index) }

    fun part2(): String = getListOfValues().asSequence().take(240)
        .mapIndexed { index, value -> index % 40 to value }
        .map { (x, value) -> if (value in (x - 1)..(x + 1)) "#" else "." }
        .chunked(40)
        .joinToString("\n") { it.joinToString("") }
}