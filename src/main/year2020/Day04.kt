package main.year2020

import java.io.File

class Day04(text : String = "passports.txt") {

    private val day = "04"
    private val year = "2020"

    private val input = File("src/resources/$year/$day/$text").readText().trim().splitToSequence("\n\n", "\r\n\r\n")
        .map { c ->
            c.splitToSequence("\n", "\r\n")
                .flatMap { d -> d.split(" ") }
                .associate { e -> e.split(":").let { (k, v) -> k to v } }
        }.toList()

    private fun valid(map: Map<String, String>): Boolean =
        map.all { (key, value) ->
            when (key) {
                "byr" -> (value.toInt() in 1920..2002)
                "iyr" -> (value.toInt() in 2010..2020)
                "eyr" -> (value.toInt() in 2020..2030)
                "hgt" -> if (value.contains("cm")) {
                    (value.replace("cm", "").toInt() in 150..193)
                } else if (value.contains("in")) {
                    (value.replace("in", "").toInt() in 59..76)
                } else false
                "hcl" -> (value.matches(Regex("#[0-9a-f]{6}")))
                "ecl" -> (value.matches(Regex("amb|blu|brn|gry|grn|hzl|oth")))
                "pid" -> (value.matches(Regex("[0-9]{9}")))
                else -> true //cid or CORRECT:CORRECT from test file
            }
        }

    private val validCheck = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    fun part1(): Int = input.count { c -> validCheck.all { f -> c.contains(f) } }

    fun part2(): Int = input.count { c -> validCheck.all { f -> c.contains(f) && valid(c)}}

}