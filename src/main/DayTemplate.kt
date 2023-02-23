package main

import java.io.File

class DayTemplate(text : String = "input.txt") {

    private val day = "" //01
    private val year = "" //2022

    private val input = File("src/resources/$year/$day/$text").readLines().toList()

    fun part1(): Int {
        return 0
    }

    fun part2(): Int {
        return 0
    }

}

