package com.example.year2025;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    @BeforeEach
    void setUp() {
        Day02.useExample();
    }

    @Test
    void part1() {
        assertEquals(1227775554, new Day02().part1());
    }

    @Test
    void part2() {
        assertEquals(4174379265L, new Day02().part2());
    }
}
