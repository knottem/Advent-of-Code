package com.example.year2025;

import com.example.template.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {

    @BeforeEach
    void setUp() {
        Day.useExample();
    }

    @Test
    void part1() {
        assertEquals(3, new Day05().part1());
    }

    @Test
    void part2() {
        assertEquals(14, new Day05().part2());
    }
}
