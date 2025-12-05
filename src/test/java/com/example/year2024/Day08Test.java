package com.example.year2024;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test {

    @BeforeEach
    void setUp() {
        Day08.useExample();
    }

    @Test
    void part1() {
        assertEquals(14, new Day08().part1());
    }

    @Test
    void part2() {
        assertEquals(34, new Day08().part2());
    }
}
