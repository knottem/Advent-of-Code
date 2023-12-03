package main.year2020

import java.io.File

class Day01(text : String = "input.txt") {

    private val day = "01"
    private val year = "2020"

    private val input = File("src/resources/$year/$day/$text").readLines().map { it.toInt() }.toList()

    fun part1(): Int {
        for (i in input.indices) {
            for (j in i + 1 until input.size) {
                if (input[i] + input[j] == 2020) {
                    return input[i] * input[j]
                }
            }
        }
        throw IllegalStateException("No two numbers found that add up to 2020")
    }

    fun part1V2(): Int = input.filter { input.contains(2020 - it) }.let { it[0] * it[1] }

    fun part2(): Int {
        for (i in input.indices) {
            for (j in i + 1 until input.size) {
                for (k in j + 1 until input.size) {
                    if (input[i] + input[j] + input[k] == 2020) {
                        return input[i] * input[j] * input[k]
                    }
                }
            }
        }
        throw IllegalStateException("No three numbers found that add up to 2020")
    }

}

