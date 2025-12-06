package com.example.template;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {

    private final String day;
    private final String year;
    private final String fileName;
    private final List<String> input;

    private static String defaultFileName = "input";

    private static boolean testEnabled = false;

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

    public static void setTest(boolean test) {
        testEnabled = test;
    }

    private List<String> readInputFile() {
        if(testEnabled) return List.of();
        try {
            Path path = Paths.get(String.format("src/main/resources/%s/%s/%s", year, day, fileName.endsWith(".txt") ? fileName : fileName + ".txt"));
            if(!Files.exists(path)){
                System.err.println("Download the input you dummy");
            }
            return Files.readAllLines(path);
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
