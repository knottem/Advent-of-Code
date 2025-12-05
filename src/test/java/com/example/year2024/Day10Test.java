package com.example.year2024;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @BeforeEach
    void setUp() {
        Day10.useExample();
    }

    @Test
    void part1() {
        assertEquals(36, new Day10().part1());
    }

    @Test
    void part2() {
        assertEquals(81, new Day10().part2());
    }
}
