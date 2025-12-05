package com.example.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.example.network.ExampleDownloader.*;
import static com.example.network.TestClassGenerator.writeTestClass;

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

    public void ensureExampleAndTests() {
        try {
            Path examplePath = Paths.get(String.format("src/main/resources/%s/%s/%s", year, day, "example.txt"));
            Path testPath = Paths.get("src/test/java/com/example/", "year" + year, String.format("Day%sTest.java", day));
            Document example = null;

            boolean exampleMissing = !Files.exists(examplePath);
            boolean testMissing = !Files.exists(testPath);

            if (exampleMissing) {
                System.out.printf("Example file %s not found, downloading...%n", examplePath);
                example = downloadExample(year, day);
                Files.createDirectories(examplePath.getParent());
                Files.writeString(examplePath, parseExample(example));
            } else {
                System.out.printf("Example file %s already exists%n", examplePath);
            }

            if (testMissing) {
                String output = String.format("Example test %s not found", testPath);
                if (example == null) {
                    output = output + ", downloading...";
                    example = downloadExample(year, day);
                }
                System.out.println(output);
                String part1Expected = parseExampleAnswer(example);
                writeTestClass(year, day, part1Expected);
            } else {
                System.out.printf("Example test %s already exists%n", testPath);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private List<String> readInputFile() {
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
