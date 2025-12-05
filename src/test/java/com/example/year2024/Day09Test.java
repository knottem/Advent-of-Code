package com.example.year2024;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

    @BeforeEach
    void setUp() {
        Day09.useExample();
    }

    @Test
    void part1() {
        assertEquals(1928, new Day09().part1());
    }

    @Test
    void part2() {
        assertEquals(2858, new Day09().part2());
    }
}
