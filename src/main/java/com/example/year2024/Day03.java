package com.example.year2024;

import com.example.template.Day;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Day {

    Pattern pattern;
    Matcher matcher;

    public Day03() {
        super("input", "03", "2024");
        pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
    }


    // Find valid mul(X,Y) instructions (1-3 digit numbers, e.g., mul(44,46)).
    // Ignore invalid characters and malformed instructions, then sum the multiplication results.
    @Override
    public long part1() {
        String input = String.join("", getInput());
        return multiply(input);
    }

    // Add support for do() (enables) and don't() (disables) instructions to control mul(X,Y) processing.
    // At start, mul is enabled; only enabled instructions contribute to the sum.
    // regex is king for this, since we can just replace everything between don't and do
    @Override
    public long part2() {
        String cleanedInput = String.join("", getInput()).replaceAll("don't\\(\\).*?do\\(\\)|don't\\(\\).*$", "");
        return multiply(cleanedInput);
    }

    private long multiply (String input) {
        long result = 0;
        matcher = pattern.matcher(input);
        while (matcher.find()) {
            result += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
        }
        return result;
    }
}
