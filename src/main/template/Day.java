package template;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {

    private final String day;
    private final String year;
    private final String fileName;
    private final List<String> input;

    public Day(String fileName, String day, String year) {
        this.day = day;
        this.year = year;
        this.fileName = fileName;
        this.input = readInputFile();
    }

    private List<String> readInputFile() {
        try {
            return Files.readAllLines(
                    Paths.get("src/resources/" + year + "/" + day + "/" + fileName));
        } catch (IOException e) {
            System.err.printf("Warning: Failed to read input file for Year %s, Day %s (%s).%n", year, day, fileName);
            return null;
        }
    }

    public String getDay() {
        return day;
    }

    public String getYear() {
        return year;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getInput() {
        return input;
    }

    public abstract long part1();

    public abstract long part2();

}
