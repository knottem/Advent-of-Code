package template;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {

    private final String day;
    private final String year;
    private final List<String> input;

    public Day(String text, String day, String year) {
        this.day = day;
        this.year = year;
        this.input = readInputFile(text);
    }

    private List<String> readInputFile(String text) {
        try {
            return Files.readAllLines(
                    Paths.get("src/resources/" + year + "/" + day + "/" + text));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDay() {
        return day;
    }

    public String getYear() {
        return year;
    }

    public List<String> getInputFile() {
        return input;
    }

    public abstract int part1();

    public abstract int part2();


}
