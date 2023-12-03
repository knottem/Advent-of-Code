package main.year2022

import java.io.File


class Day05(text : String = "input.txt") {

    private val day = "05"
    private val year = "2022"

    val input = File("src/resources/$year/$day/$text").readLines()

    private val moves = mutableListOf<String>()
    private val piles = mutableListOf<MutableList<Char>>()

    // getting the moves and piles from the input, took the longest time to figure out and the problem after that was quite easy
    // not the prettiest code, but it works
    private fun inputParse() {
        moves.clear()
        piles.clear()
        val data = mutableListOf<String>()
        for (line in input) {
            if (line.startsWith("move")){
                moves.add(line)
            } else if (line.contains("1   2")){
                for (i in 0 until line.replace(" ".toRegex(), "").takeLast(1).toInt()) {
                    piles.add(mutableListOf())
                }
            } else {
                data.add(line)
            }
        }
        data.forEach { line ->
            line.forEachIndexed { index, c ->
                if (c in 'A'..'Z') {
                    val pileIndex = (index - 1) / 4
                    piles[pileIndex].add(c)
                }
            }
        }
        piles.forEach{ it.reverse() }
    }

    fun part1(): String {
        inputParse()
        moves.forEach { move ->
            val parts = move.split(" ")
            val amount = parts[1].toInt()
            val from = parts[3].toInt() - 1
            val to = parts[5].toInt() - 1

            for(i in 0 until amount){
                piles[to].add(piles[from].removeAt(piles[from].lastIndex))
            }
        }
        return piles.joinToString("") { it.takeLast(1).toString() }.replace("[", "").replace("]", "")
    }

    fun part2(): String {
        inputParse()
        moves.forEach { move ->
            val parts = move.split(" ")
            val amount = parts[1].toInt()
            val from = parts[3].toInt() - 1
            val to = parts[5].toInt() - 1

            val pile = piles[from].takeLast(amount)
            for (i in 0 until amount) piles[from].removeLast()
            piles[to].addAll(pile)
        }
        return piles.joinToString("") { it.takeLast(1).toString() }.replace("[", "").replace("]", "")
    }
}

