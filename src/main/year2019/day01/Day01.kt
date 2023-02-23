package main.year2019.day01

import java.io.File

class Day01(text : String = "input.txt") {

    private val day = "01"
    private val year = "2019"

    private val input = File("src/resources/$year/$day/$text").readLines().toList().map { it.toInt() }.toIntArray()

    //getting the sum as int, so we can round it down to the nearest Int and we get the correct amount of fuel
    fun part1(): Int = input.sumOf { it / 3 - 2 }
    fun part2(): Int = input.sumOf { fuel(it) }

    //recursive to get the amount of fuel needed for the fuel
    private fun fuel(it: Int) : Int = if (it / 3 - 2 > 0) it / 3 - 2 + fuel(it / 3 - 2) else 0

}

