package year2017;

import template.Day;

public class Day01 extends Day {

    String input;

    public Day01() {
        super("input", "01", "2017");
        input = String.join(" ", getInput());
    }

    // Calculate the sum of digits in a circular string where each digit matches the one directly after it
    @Override
    public long part1() {
        long result = 0;
        if (input.charAt(0) == input.charAt(input.length() - 1)) {
            result += Character.getNumericValue(input.charAt(0));
        }
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i - 1) == input.charAt(i)) {
                result += Character.getNumericValue(input.charAt(i));
            }
        }
        return result;
    }

    // Calculate the sum of digits in a circular string where each digit matches the one halfway around the string.
    @Override
    public long part2() {
        long result = 0;
        int half = input.length() / 2;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt((i + half) % input.length())) {
                result += Character.getNumericValue(input.charAt(i));
            }
        }
        return result;
    }
}
