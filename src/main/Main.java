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
        days.add(new Day06());


        for (Day day : days) {
            System.out.printf("""
                    Day %s, Year %s, File: %s
                    Part 1: %s
                    Part 2: %s
                    %n""", day.getDay(), day.getYear(), day.getFileName(), day.part1(), day.part2());
        }
    }
}
