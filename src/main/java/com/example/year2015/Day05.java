package com.example.year2015;

import com.example.template.Day;

import java.util.regex.Pattern;

public class Day05 extends Day {

    public Day05() {
        super("input.txt", "05", "2015");

    }

    @Override
    public long part1() {
        return  getInput().stream()
                .filter(c -> !Pattern.compile("ab|cd|pq|xy").matcher(c).find())
                .filter(c -> Pattern.compile("(.*[aeiou]){3}").matcher(c).find())
                .filter(c -> Pattern.compile("(.)\\1").matcher(c).find())
                .count();
    }

    @Override
    public long part2() {
        return  getInput().stream()
                .filter(c -> Pattern.compile("(..).*\\1").matcher(c).find())
                .filter(c -> Pattern.compile("(.).\\1").matcher(c).find())
                .count();
    }
}
