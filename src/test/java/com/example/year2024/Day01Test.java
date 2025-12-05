package com.example.year2024;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

    @BeforeEach
    void setUp() {
        Day01.useExample();
    }

    @Test
    void part1() {
        assertEquals(11, new Day01().part1());
    }

    @Test
    void part2() {
        assertEquals(31, new Day01().part2());
    }
}