package com.example.year2024;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test {

    @BeforeEach
    void setUp() {
        Day06.useExample();
    }

    @Test
    void part1() {
        assertEquals(41, new Day06().part1());
    }

    @Test
    void part2() {
        assertEquals(6, new Day06().part2());
    }
}
