package com.example.year2025;

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
        assertEquals(40, new Day08().part1());
    }

    @Test
    void part2() {
        assertEquals(25272, new Day08().part2());
    }

}
