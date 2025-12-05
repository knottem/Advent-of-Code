package com.example.year2024;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {

    @BeforeEach
    void setUp() {
        Day02.useExample();
    }

    @Test
    void part1() {
        assertEquals(2, new Day02().part1());
    }

    @Test
    void part2() {
        assertEquals(4, new Day02().part2());
    }
}