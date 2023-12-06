package year2023;

import template.Day;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01 extends Day {

    public Day01() {
      super("input.txt", "01", "2023");
    }

    // Part 1 - Find the first and last digit on each line and add them together to a 2-digit number.
    // If there is only one digit, add it to itself to create a 2-digit number.
    @Override
    public long part1() {
        int sum = 0;
        for (String s : getInput()) {
            int bigNumber = Integer.parseInt(s.replaceAll("[^0-9-]", ""));
            if(bigNumber < 10) {
                sum += (bigNumber * 10) + bigNumber;
            } else if(bigNumber > 10 && bigNumber < 100){
                sum += bigNumber;
            } else {
                int lastDigit = Math.abs(bigNumber % 10);
                int firstDigit = Integer.parseInt(String.valueOf(Math.abs(bigNumber)).substring(0, 1));
                sum += (firstDigit * 10) + lastDigit;
            }
        }
        return sum;
    }
    static String regex = "(one|two|three|four|five|six|seven|eight|nine|[1-9])";
    static List<String> digits = List.of(
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    );

    private String extractFirst(String l) {
        Matcher matcher = Pattern.compile(regex).matcher(l);
        return matcher.find() ? toDigit(matcher.group(1)) : "";
    }

    private String extractLast(String l) {
        Matcher lastMatcher = Pattern.compile(".*" + regex).matcher(l);
        return lastMatcher.find() ? toDigit(lastMatcher.group(1)) : "";
    }

    private String toDigit(String s) {
        int i = digits.indexOf(s);
        return (i >= 0) ? String.valueOf(i + 1) : s;
    }

    @Override
    public long part2() {
        int sum = 0;
        for (String line : getInput()) {
            sum += Integer.parseInt(extractFirst(line) + extractLast(line));
        }
        return sum;
    }
}
