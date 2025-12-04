package com.example.year2024;

import com.example.template.Day;

import java.util.*;

public class Day11 extends Day {

    List<Long> stones;

    public Day11() {
        super("input", "11", "2024");
        stones = Arrays.stream(getInput().get(0).split(" "))
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();
    }

    // Part 1: Simulate stone evolution over 25 blinks
    // - Start with the initial arrangement of stones.
    // - For each blink, transform each stone based on the rules:
    //   1. Replace 0 with 1.
    //   2. Split stones with an even number of digits into two stones.
    //   3. Multiply other stones by 2024.
    // - Return the total number of stones after 25 blinks.
    @Override
    public long part1() {
        return countStonesAfterBlinks(stones, 25);
    }

    // Part 2: Simulate stone evolution over 75 blinks
    // RIP my shitty solution for part 1, not enough heap space for 75 blinks
    @Override
    public long part2() {
        return countStonesAfterBlinks(stones, 75);
    }

    // old solution for part 1
    /*
    private List<Long> simulateStoneEvolution(List<Long> stones, int blinks) {
        List<Long> current = new ArrayList<>(stones);
        for (int blink = 1; blink <= blinks; blink++) {
            List<Long> next = new ArrayList<>();
            for (long stone : current) {
                if (stone == 0) {
                    next.add(1L); // Rule 1: Replace 0 with 1
                } else if (Long.toString(Math.abs(stone)).length() % 2 == 0) { // Even number of digits
                    next.addAll(splitStone(stone)); // Rule 2: Split stones with even number of digits
                } else {
                    next.add(stone * 2024); // Rule 3: Multiply by 2024
                }
            }
            current = next;
            //System.out.printf("After %d blink%s: %s%n", blink, (blink > 1 ? "s" : ""), current);
        }
        return current;
    }
    */

    private List<Long> splitStone(long stone) {
        String digits = Long.toString(Math.abs(stone)); // Use absolute value for splitting
        int mid = digits.length() / 2;

        long left = Long.parseLong(digits.substring(0, mid));
        long right = Long.parseLong(digits.substring(mid));

        // Preserve the sign for the left part
        if (stone < 0) {
            left = -left;
        }

        return List.of(left, right);
    }

    private long countStonesAfterBlinks(List<Long> stones, int blinks) {
        Map<Long, Long> stoneCounts = new HashMap<>();

        // Initialize the counts with the input stones
        for (long stone : stones) {
            stoneCounts.put(stone, stoneCounts.getOrDefault(stone, 0L) + 1);
        }

        for (int blink = 1; blink <= blinks; blink++) {
            Map<Long, Long> nextStoneCounts = new HashMap<>();
            for (Map.Entry<Long, Long> entry : stoneCounts.entrySet()) {
                long stone = entry.getKey();
                long count = entry.getValue();

                if (stone == 0) {
                    nextStoneCounts.put(1L, nextStoneCounts.getOrDefault(1L, 0L) + count);
                } else if (Long.toString(Math.abs(stone)).length() % 2 == 0) {
                    List<Long> split = splitStone(stone);
                    nextStoneCounts.put(split.get(0), nextStoneCounts.getOrDefault(split.get(0), 0L) + count);
                    nextStoneCounts.put(split.get(1), nextStoneCounts.getOrDefault(split.get(1), 0L) + count);
                } else {
                    long multiplied = stone * 2024;
                    nextStoneCounts.put(multiplied, nextStoneCounts.getOrDefault(multiplied, 0L) + count);
                }
            }
            stoneCounts = nextStoneCounts; // Move to the next generation
            //System.out.printf("After %d blink%s: Total stones = %d%n", blink, (blink > 1 ? "s" : ""), stoneCounts.values().stream().mapToLong(Long::longValue).sum());
        }

        // Total number of stones
        return stoneCounts.values().stream().mapToLong(Long::longValue).sum();
    }

}
