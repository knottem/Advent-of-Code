import template.Day;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private void run() {
        //print2019();
        //print2020();
        //print2022();
        //print2023();
        print2024();
    }

    private void print2024() {
        List<Day> days2024 = new ArrayList<>();
        days2024.add(new year2024.Day01());
        days2024.add(new year2024.Day02());
        //days2024.add(new year2024.Day03());
        //days2024.add(new year2024.Day04());
        //days2024.add(new year2024.Day05());
        //days2024.add(new year2024.Day06());
        //days2024.add(new year2024.Day07());
        //days2024.add(new year2024.Day09());
        printDays(days2024);
    }

    private void print2023() {
        List<Day> days2023 = new ArrayList<>();
        days2023.add(new year2023.Day01());
        days2023.add(new year2023.Day02());
        days2023.add(new year2023.Day03());
        days2023.add(new year2023.Day04());
        //days2023.add(new year2023.Day05()); //part 2 takes 15min+
        days2023.add(new year2023.Day06());
        days2023.add(new year2023.Day07());
        days2023.add(new year2023.Day08());
        days2023.add(new year2023.Day09());
        days2023.add(new year2023.Day10());
        days2023.add(new year2023.Day11());
        days2023.add(new year2023.Day15());
        printDays(days2023);
    }

    private void print2022() {
        List<Day> days2022 = new ArrayList<>();
        days2022.add(new year2022.Day02());
        days2022.add(new year2022.Day03());
        days2022.add(new year2022.Day04());
        days2022.add(new year2022.Day05());
        days2022.add(new year2022.Day10());
        printDays(days2022);
    }

    private void print2020() {
        List<Day> days2020 = new ArrayList<>();
        days2020.add(new year2020.Day01());
        days2020.add(new year2020.Day04());
        printDays(days2020);
    }

    private void print2019() {
        List<Day> days2019 = new ArrayList<>();
        days2019.add(new year2019.Day01());
        days2019.add(new year2019.Day02());
        days2019.add(new year2019.Day03());
        days2019.add(new year2019.Day04());
        printDays(days2019);
    }

    private void printDays(List<Day> days) {
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

    public static void main(String[] args) {
        new Main().run();
    }
}
