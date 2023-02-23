package year2019.day04

import java.io.File

class Day04(text : String = "input.txt") {

    private val day = "04"
    private val year = "2019"

    private val input = File("src/resources/$year/$day/$text").readText().take(6).toInt()
    private val input2 = File("src/resources/$year/$day/$text").readText().takeLast(6).toInt()

    fun part1(): Int = (input..input2).count { c -> (c.toString().zipWithNext().all { it.first <= it.second } && c.toString().zipWithNext().any { it.first == it.second })}
    fun part2(): Int = (input..input2).count { c -> (c.toString().zipWithNext().all { it.first <= it.second } && c.toString().zipWithNext().any { it.first == it.second }) && c.toString().groupingBy { it }.eachCount().any { it.value == 2 } }

}

