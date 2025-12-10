package com.example.year2025;

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
        assertEquals(21, new Day07().part1());
    }

    @Test
    void part2() {
        assertEquals(40, new Day07().part2());
    }

}
