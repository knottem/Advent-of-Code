package main.year2019.day02

import java.io.File

class Day02(text : String = "input.txt") {

    private val day = "02"
    private val year = "2019"

    private val input = File("src/resources/$year/$day/$text").readText().split(",").map { it.toInt() }.toIntArray()


    private fun calculate(input: IntArray): Int {
        for (i in input.indices step 4) {
            if (input[i] == 99) break
            val a = input[input[i + 1]]
            val b = input[input[i + 2]]
            val c = input[i + 3]
            input[c] = if (input[i] == 1) a + b else a * b
        }
        return input[0]
    }

    fun part1(): Int {
        val input = input.copyOf()
        if (input.size > 12) {
            input[1] = 12
            input[2] = 2
        }
        return calculate(input)
    }

    fun part2(): Int {
        for (i in 0..99) {
            for (j in 0..99) {
                val input = input.copyOf()
                input[1] = i
                input[2] = j
                if (calculate(input) == 19690720) return 100 * i + j
            }
        }
        return 0
    }
}

