package com.example.year2025;

import com.example.template.Day;

import java.util.*;

public class Day05 extends Day {

    public Day05() {
        super("05", "2025");
    }

    record Range(long start, long end) {}

    @Override
    public long part1() {
        long total = 0;
        List<Range> validNumbers = new ArrayList<>();
        List<Long> fruits = new ArrayList<>();
        for (int i = 0; i < getInput().size(); i++) {
            if(getInput().get(i).contains("-")) {
                String[] parts = getInput().get(i).split("-");
                validNumbers.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
            } else if (!getInput().get(i).isEmpty()){
                fruits.add(Long.parseLong(getInput().get(i)));
            }
        }
        for (Long fruit : fruits) {
            for (Range range : validNumbers) {
                if (fruit >= range.start && fruit <= range.end) {
                    total++;
                    break;
                }
            }
        }
        return total;
    }

    @Override
    public long part2() {
        List<Range> validNumbers = new ArrayList<>();
        for (int i = 0; i < getInput().size(); i++) {
            if(getInput().get(i).contains("-")) {
                String[] parts = getInput().get(i).split("-");
                validNumbers.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
            }
        }
        validNumbers.sort(Comparator.comparingLong(Range::start));
        return getTotalValidIds(validNumbers);
    }

    private static long getTotalValidIds(List<Range> ranges) {
        long total = 0;
        long currentStart = ranges.getFirst().start;
        long currentEnd   = ranges.getFirst().end;
        for (Range r : ranges) {
            if (r.start <= currentEnd + 1) {
                currentEnd = Math.max(currentEnd, r.end);
            } else {
                total += (currentEnd - currentStart + 1);
                currentStart = r.start;
                currentEnd = r.end;
            }
        }
        total += (currentEnd - currentStart + 1);
        return total;
    }
}
