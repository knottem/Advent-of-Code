package com.example.year2025;

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
        assertEquals(4277556L, new Day06().part1());
    }

    @Test
    void part2() {
        assertEquals(3263827L, new Day06().part2());
    }
}
