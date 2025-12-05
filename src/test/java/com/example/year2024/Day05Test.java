package com.example.year2024;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {

    @BeforeEach
    void setUp() {
        Day05.useExample();
    }

    @Test
    void part1() {
        assertEquals(143, new Day05().part1());
    }

    @Test
    void part2() {
        assertEquals(123, new Day05().part2());
    }
}
