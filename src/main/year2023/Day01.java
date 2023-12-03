package year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01 {

    private List<String> input;

    public Day01(String text) {
        String day = "01"; //day
        String year = "2023"; //year
        try{
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Part 1 - Find the first and last digit on each line and add them together to a 2-digit number.
    // If there is only one digit, add it to itself to create a 2-digit number.
    public int part1() {
        int sum = 0;
        for (String s : input) {
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

    // Method to get first digit and last digit from a string that can contain both digits and letters (e.g. "one" or "two")
    // Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".
    //
    //Equipped with this new information, you now need to find the real first and last digit on each line. For example:
    // 1234 -> 14
    // 236twoknbxlczgd -> 22
    // s2eight6bhshvmsevensix -> 26
    // three1sk4hnine -> 39
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

    public int part2() {
        int sum = 0;
        for (String line : input) {
            sum += Integer.parseInt(extractFirst(line) + extractLast(line));
        }
        return sum;
    }
}
