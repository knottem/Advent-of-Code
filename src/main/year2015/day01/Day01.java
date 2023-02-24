package year2015.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class Day01 {

    private List<String> input;

    public Day01(String text) {
        String day = "01";
        String year = "2015";
        try {
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    public int part1() {
        return (int) input.stream()
                .filter(c -> !Pattern.compile("ab|cd|pq|xy").matcher(c).find())
                .filter(c -> Pattern.compile("(.*[aeiou]){3}").matcher(c).find())
                .filter(c -> Pattern.compile("(.)\\1").matcher(c).find())
                .count();
    }

    public int part2() {
        return (int) input.stream()
                .filter(c -> Pattern.compile("(..).*\\1").matcher(c).find())
                .filter(c -> Pattern.compile("(.).\\1").matcher(c).find())
                .count();
    }
}
