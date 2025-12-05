package com.example.year2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {


    @Test
    void part1() {
        Day03.useExample();
        assertEquals(161, new Day03().part1());
    }

    @Test
    void part2() {
        Day03.useExample("example2.txt");
        assertEquals(48, new Day03().part2());
    }
}