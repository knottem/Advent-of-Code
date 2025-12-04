package com.example.year2025;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    @BeforeEach
    void setUp() {
        Day03.useExample();
    }

    @Test
    void part1() {
        assertEquals(357, new Day03().part1());
    }

    @Test
    void part2() {
        assertEquals(3121910778619L, new Day03().part2());
    }
}
