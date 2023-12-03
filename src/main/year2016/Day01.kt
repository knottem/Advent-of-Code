package main.year2016

import java.io.File
import kotlin.math.abs

class Day01(text : String = "input.txt") {

    class Orientation{
        var direction = "N"
        var blocksX = 0
        var blocksY = 0
    }

    private val day = "01"
    private val year = "2016"

    private val input = File("src/resources/$year/$day/$text").readText()

    private fun walk(o: Orientation, turnDirection: Char, steps: Int) {
        when (o.direction) {
            "N" -> {
                o.direction = if (turnDirection == 'R') "E" else "W"
                o.blocksX += if (turnDirection == 'R') steps else -steps
            }
            "E" -> {
                o.direction = if (turnDirection == 'R') "S" else "N"
                o.blocksY += if (turnDirection == 'R') -steps else steps
            }
            "S" -> {
                o.direction = if (turnDirection == 'R') "W" else "E"
                o.blocksX += if (turnDirection == 'R') -steps else steps
            }
            "W" -> {
                o.direction = if (turnDirection == 'R') "N" else "S"
                o.blocksY += if (turnDirection == 'R') steps else -steps
            }
        }
    }

    fun part1() : Int = input.trim().split(", ").fold(Orientation()) { o, s -> walk(o, s[0], s.substring(1).toInt())
        o
    }.let { abs(it.blocksX) + abs(it.blocksY) }

    fun part2() : Int {
        var x = 0
        var y = 0
        return input.trim().split(", ").fold(Pair(Orientation(), mutableSetOf(Pair(0, 0)))) { (o, visited), s ->
            walk(o, s[0], s.substring(1).toInt())
            repeat(s.substring(1).toInt()) {
                when (o.direction) {
                    "N" -> y++
                    "E" -> x++
                    "S" -> y--
                    "W" -> x--
                }
                if (Pair(x, y) in visited) {
                    return abs(x) + abs(y)
                } else {
                    visited.add(Pair(x, y))
                }
            }
            Pair(o, visited)
        }.let { 0 }
    }
}

