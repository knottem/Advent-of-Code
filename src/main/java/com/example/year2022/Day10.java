package com.example.year2022;

import com.example.template.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day10 extends Day {

    private final Set<Integer> set = Set.of(20, 60, 100, 140, 180, 220);

    public Day10() {
        super("input.txt", "10", "2022");
    }

    private List<Integer> getListOfValues() {
        int value = 1;
        List<Integer> cycle = new ArrayList<>();
        cycle.add(value);

        for (String line : getInput()) {
            cycle.add(value);
            if (line.startsWith("addx")) {
                value += Integer.parseInt(line.replace("addx ", ""));
                cycle.add(value);
            }
        }

        return cycle;
    }

    @Override
    public long part1() {
        List<Integer> values = getListOfValues();
        return IntStream.range(0, values.size())
                .filter(index -> set.contains(index + 1))
                .mapToLong(index -> (long) values.get(index) * set.stream()
                        .filter(setValue -> setValue == index + 1)
                        .findFirst()
                        .orElse(0))
                .sum();
    }

    @Override
    public long part2() {
        List<Integer> values = getListOfValues();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 240; i++) {
            int value = values.get(i);
            int x = i % 40;
            if (value >= x - 1 && value <= x + 1) {
                result.append("#");
            } else {
                result.append(".");
            }

            if (x == 39) {
                result.append("\n");
            }
        }
        System.out.println(result);
        return 0;
    }

}
