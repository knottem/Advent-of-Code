package com.example.year2024;

import com.example.template.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 extends Day {

    public Day07() {
        super("input", "07", "2024");
    }

    // Each line represents a single equation. The test value appears before the colon on each line;
    // it is your job to determine whether the remaining numbers can be combined with operators to produce the test value.
    // Operators are always evaluated left-to-right, not according to precedence rules.
    // Furthermore, numbers in the equations cannot be rearranged. Glancing into the jungle, you can see elephants
    // holding two different types of operators: add (+) and multiply (*).
    @Override
    public long part1() {
        return solvePart(false);
    }

    // The concatenation operator (||) combines the digits from its left and right inputs into a single number.
    // For example.txt, 12 || 345 would become 12345. All operators are still evaluated left-to-right.
    @Override
    public long part2() {
        return solvePart(true);
    }

    private long solvePart(boolean part2) {
        long sum = 0;
        Pattern pattern = Pattern.compile("\\d+");

        for (String line : getInput()) {
            Matcher matcher = pattern.matcher(line);
            List<Long> numbers = new ArrayList<>();
            while (matcher.find()) {
                numbers.add(Long.parseLong(matcher.group()));
            }

            long target = numbers.remove(0);
            if (matchesTarget(target, numbers, 0, numbers.get(0), part2)) {
                sum += target;
            }
        }

        return sum;
    }

    // Recursive to find if it works no matter the length
    // Part 2 we added combined
    private boolean matchesTarget(long target, List<Long> numbers, int index, long currentResult, boolean part2) {
        if (index == numbers.size() - 1) {
            return currentResult == target;
        }

        long nextNum = numbers.get(index + 1);

        boolean result = matchesTarget(target, numbers, index + 1, currentResult + nextNum, part2) ||
                matchesTarget(target, numbers, index + 1, currentResult * nextNum, part2);
        
        if (part2) {
            long combined = Long.parseLong(String.valueOf(currentResult) + nextNum);
            result = result || matchesTarget(target, numbers, index + 1, combined, true);
        }

        return result;
    }
}
