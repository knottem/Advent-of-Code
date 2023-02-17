package day01

import java.io.File
//https://adventofcode.com/2022/day/1
class Day01(text : String = "input.txt") {

    private val input = "src/main/resources/01/$text"

    private fun getTextAsInt(text : String): List<List<Int>> {
        val file = File(text)
        val numList = mutableListOf<List<Int>>()
        var currentList = mutableListOf<Int>()
        file.forEachLine { line ->
            if (line.isBlank()) {
                if (currentList.isNotEmpty()) {
                    numList.add(currentList)
                    currentList = mutableListOf()
                }
            } else {
                currentList += line.split(" ").map { it.toInt() }
            }
        }
        if (currentList.isNotEmpty()) {
            numList.add(currentList)
        }
        return numList
    }

    fun part1(): Int {
        val test2 = mutableListOf<Int>()
        getTextAsInt(input).forEach { test2.add(it.sum())}
        return test2.max()
    }

    fun part2(): Int {
        val test2 = mutableListOf<Int>()
        getTextAsInt(input).forEach { test2.add(it.sum()) }
        return test2.sortedDescending().take(3).sum()
    }
}