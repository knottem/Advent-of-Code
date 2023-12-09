import template.Day;
import year2023.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Day> days = new ArrayList<>();
        //days.add(new Day01());
        //days.add(new Day02());
        //days.add(new Day03());
        //days.add(new Day04());
        //days.add(new Day05());
        //days.add(new Day06());
        //days.add(new Day07());
        //days.add(new Day08());
        days.add(new Day09());


        for (Day day : days) {
            System.out.printf("Day %s, Year %s, File: %s%n", day.getDay(), day.getYear(), day.getFileName());
            double start = System.nanoTime();
            long part1Result = day.part1();
            double end = System.nanoTime();
            System.out.printf("Part 1: %d%nTime: %s s%n", part1Result, String.format("%.6f", (end - start) / 1_000_000_000));
            start = System.nanoTime();
            long part2Result = day.part2();
            end = System.nanoTime();
            System.out.printf("Part 2: %d%nTime: %s s%n%n", part2Result, String.format("%.6f", (end - start) / 1_000_000_000));
        }
    }
}
