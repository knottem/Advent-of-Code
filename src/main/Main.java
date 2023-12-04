package main;

import template.Day;
import year2023.Day01;
import year2023.Day02;
import year2023.Day03;
import year2023.Day04;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Day> days = new ArrayList<>();
        //days.add(new Day01());
        //days.add(new Day02());
        //days.add(new Day03());
        days.add(new Day04());


        for (Day day : days) {
            System.out.printf("""
                    Day %s
                    Part 1: %s
                    Part 2: %s
                    %n""", day.getDay(), day.part1(), day.part2());
        }
    }
}
