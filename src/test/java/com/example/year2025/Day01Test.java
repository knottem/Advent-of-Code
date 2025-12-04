package com.example.year2025;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @BeforeEach
    void setUp() {
        Day01.useExample();
    }

    @Test
    void part1() {
        assertEquals(3, new Day01().part1());
    }

    @Test
    void part2() {
        assertEquals(6, new Day01().part2());
    }
}
