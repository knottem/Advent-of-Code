package utils;

import template.Day;

import java.util.List;

public class DayRunner {

    public void executeYear(String year, List<Integer> skippedDays) {
        System.out.println("========================================");
        System.out.printf("Executing Year: %s%n", year);
        System.out.println("========================================");
        List<Day> days = DayRegistry.getDaysForYear(year);
        if (days.isEmpty()) {
            System.out.printf("No days registered for Year: %s%n", year);
            return;
        }
        if (!skippedDays.isEmpty()) {
            System.out.printf("Skipping Days: %s%n", skippedDays);
            System.out.println();
        }

        for (Day day : days) {
            int dayNumber = Integer.parseInt(day.getDay());
            if (skippedDays.contains(dayNumber)) {
                continue;
            }

            if (!day.isInputAvailable()) {
                System.err.printf("Warning: Failed to read input file for Year %s, Day %d (%s).%n",
                        day.getYear(), dayNumber, day.getFileName());
                System.err.flush();
            } else {
                executeDay(day);
            }
        }
        System.out.println();
    }

    public void executeYear(String year) {
        executeYear(year, List.of());
    }

    public void executeDay(Day day) {
        System.out.printf("Day %s, Year %s, File: %s%n", day.getDay(), day.getYear(), day.getFileName());
        try {
            double start = System.nanoTime();
            long part1Result = day.part1();
            double end = System.nanoTime();
            System.out.printf("Part 1: %d%nTime: %s s%n", part1Result, String.format("%.6f", (end - start) / 1_000_000_000));
        } catch (Exception e) {
            System.err.printf("Error occurred while executing Part 1 Day %s, Year %s: %s%n",
                    day.getDay(), day.getYear(), e.getMessage());
        }
        try {
            double start = System.nanoTime();
            long part2Result = day.part2();
            double end = System.nanoTime();
            System.out.printf("Part 2: %d%nTime: %s s%n", part2Result, String.format("%.6f", (end - start) / 1_000_000_000));
        } catch (Exception e) {
            System.err.printf("Error occurred while executing Part 2 Day %s, Year %s: %s%n",
                    day.getDay(), day.getYear(), e.getMessage());
        }
        System.out.println();
    }
}
