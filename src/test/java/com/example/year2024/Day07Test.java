package com.example.year2024;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day07Test {

    @BeforeEach
    void setUp() {
        Day07.useExample();
    }

    @Test
    void part1() {
        assertEquals(3749, new Day07().part1());
    }

    @Test
    void part2() {
        assertEquals(11387, new Day07().part2());
    }
}
