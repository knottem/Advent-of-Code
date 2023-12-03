package main.year2019

import java.io.File
import kotlin.math.abs

class Day03(text : String = "input.txt") {

    private val day = "03"
    private val year = "2019"

    val input = File("src/resources/$year/$day/$text").readLines().toList()

    private fun getWirePath(wire: List<String>): List<Pair<Int, Int>> {
        val path = mutableListOf<Pair<Int, Int>>()
        var current = Pair(0, 0)
        for (i in wire) {
            val direction = i[0]
            val distance = i.substring(1).toInt()
            val orientation = when (direction) {
                'U' -> Pair(0, 1)
                'D' -> Pair(0, -1)
                'L' -> Pair(-1, 0)
                'R' -> Pair(1, 0)
                else -> throw Exception("Invalid direction")
            }
            for (j in 1..distance) {
                current = Pair(current.first + orientation.first, current.second + orientation.second)
                path.add(current)
            }
        }
        return path
    }

    fun part1(): Int = getWirePath(input[0].split(","))
        .intersect(getWirePath(input[1].split(",")).toSet()).minOfOrNull { abs(it.first) + abs(it.second) } ?: 0

    fun part2(): Int = getWirePath(input[0].split(","))
        .intersect(getWirePath(input[1].split(",")).toSet())
        .minOfOrNull { getWirePath(input[0].split(",")).indexOf(it) + getWirePath(input[1].split(",")).indexOf(it) + 2 } ?: 0


}
