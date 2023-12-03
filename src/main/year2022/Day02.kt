package main.year2022

import java.io.File

//https://adventofcode.com/2022/day/2
class Day02(text : String = "input.txt") {

    private val day = "02"

    private val input = File("src/resources/2022/$day/$text").readLines().toList()

    private fun gameOfRps(text : String): Int {
        var sum = 0
        if(text.startsWith("A")){ // rock
            if(text.endsWith("Y")){
                sum += 2+6
            } else if(text.endsWith("X")){
                sum += 1+3
            } else if(text.endsWith("Z")){
                sum += 3+0
            }
        } else if(text.startsWith("B")){
            if(text.endsWith("Y")){
                sum += 2+3
            } else if(text.endsWith("X")){
                sum += 1+0
            } else if(text.endsWith("Z")){
                sum += 3+6
            }
        } else if(text.startsWith("C")){
            if(text.endsWith("Y")){
                sum += 2+0
            } else if(text.endsWith("X")){
                sum += 1+6
            } else if(text.endsWith("Z")){
                sum += 3+3
            }
        }
        return sum
    }

    private fun riggedGameOfRps(text : String): Int {
        var sum = 0
        if(text.startsWith("A")){ // rock
            if(text.endsWith("Y")){
                sum += 1+3
            } else if(text.endsWith("X")){
                sum += 3+0
            } else if(text.endsWith("Z")){
                sum += 2+6
            }
        } else if(text.startsWith("B")){ // paper
            if(text.endsWith("Y")){
                sum += 2+3
            } else if(text.endsWith("X")){
                sum += 1+0
            } else if(text.endsWith("Z")){
                sum += 3+6
            }
        } else if(text.startsWith("C")){ // scissors
            if(text.endsWith("Y")){
                sum += 3+3
            } else if(text.endsWith("X")){
                sum += 2+0
            } else if(text.endsWith("Z")){
                sum += 1+6
            }
        }
        return sum
    }

    fun part1(): Int = input.sumOf { gameOfRps(it) }
    fun part2(): Int = input.sumOf { riggedGameOfRps(it) }

}