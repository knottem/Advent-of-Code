package day03

import java.io.File

//https://adventofcode.com/2022/day/3
class Day03(text : String = "input.txt") {

    private val day = "03"
    private val input = File("src/main/resources/$day/$text").readLines().asSequence()

    //code by https://github.com/osipxd/advent-of-code-2022/blob/main/src/Day03.kt
    private fun Sequence<List<String>>.sumOfPriorities(): Int {
        return map { it.map(String::toSet).reduce(Set<Char>::intersect).single() }
            .sumOf { if (it in 'a'..'z') it - 'a' + 1 else it - 'A' + 27 }
    }

    fun part1(): Int = input.map { it.chunked(it.length / 2) }.sumOfPriorities()
    fun part2(): Int = input.chunked(3).sumOfPriorities()

}

