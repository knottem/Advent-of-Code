package main.template

import java.io.File

abstract class DayKotlin(text : String = "input.txt") {

    private val day = ""
    private val year = ""

    private val input = File("src/resources/$year/$day/$text").readLines().toList()
    abstract fun part1()
    abstract fun part2()

}

