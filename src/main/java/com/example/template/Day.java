package com.example.template;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {

    private final String day;
    private final String year;
    private final String fileName;
    private final List<String> input;

    private static String defaultFileName = "input";

    public Day(String fileName, String day, String year) {
        this.day = day;
        this.year = year;
        this.fileName = fileName;
        this.input = readInputFile();
    }

    public Day(String day, String year) {
        this.day = day;
        this.year = year;
        this.fileName = defaultFileName;
        this.input = readInputFile();
    }

    public static void useExample() {
        defaultFileName = "example";
    }

    public static void useExample(String fileName) {
        defaultFileName = fileName;
    }

    private List<String> readInputFile() {
        try {
            //System.out.println("Reading input file: " + fileName + " for day " + day + " in year " + year);
            String fullPath = String.format("src/main/resources/%s/%s/%s", year, day, fileName.endsWith(".txt") ? fileName : fileName + ".txt");
            return Files.readAllLines(Paths.get(fullPath));
        } catch (IOException e) {
            System.err.println("Failed to read input file: " + fileName + " for day " + day + " in year " + year);
            return List.of();
        }
    }

    public boolean isInputAvailable() {
        return !input.isEmpty();
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
