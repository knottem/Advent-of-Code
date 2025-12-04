package com.example.year2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    @Test
    void part1_example1() {
        Day12.useExample();
        assertEquals(140, new Day12().part1());
    }

    @Test
    void part1_example2() {
        Day12.useExample("example2");
        assertEquals(772, new Day12().part1());
    }

    @Test
    void part1_example3() {
        Day12.useExample("example3");
        assertEquals(1930, new Day12().part1());
    }

    @Test
    void part2() {
    }
}