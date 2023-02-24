package year2015.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {

    private List<String> input;

    public Day02(String text) {
        String day = "02"; //day
        String year = "2015"; //year
        try {
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int part1() {
        int paper = 0;
        for (String s : input) {
            int length = Integer.parseInt(s.split("x")[0]);
            int width = Integer.parseInt(s.split("x")[1]);
            int height = Integer.parseInt(s.split("x")[2]);
            int area = 2 * length * width + 2 * width * height + 2 * height * length;
            int smallestSide = Math.min(Math.min(length * width, width * height), height * length);
            paper += area + smallestSide;
        }
        return paper;
    }


    public int part2() {
        int ribbon = 0;
        for (String s : input) {
            int length = Integer.parseInt(s.split("x")[0]);
            int width = Integer.parseInt(s.split("x")[1]);
            int height = Integer.parseInt(s.split("x")[2]);
            int smallestPerimeter = Math.min(Math.min(2 * length + 2 * width, 2 * width + 2 * height), 2 * height + 2 * length);
            int volume = length * width * height;
            ribbon += smallestPerimeter + volume;
        }
        return ribbon;
    }

    public static void main(String[] args) {
        Day02 day02 = new Day02("input.txt");
        System.out.println("Part 1: " + day02.part1());
        System.out.println("Part 2: " + day02.part2());
    }
}

