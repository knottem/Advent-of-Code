package com.example.year2024;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {

    @BeforeEach
    void setUp() {
        Day04.useExample();
    }

    @Test
    void part1() {
        assertEquals(18, new Day04().part1());
    }

    @Test
    void part2() {
        assertEquals(9, new Day04().part2());
    }
}