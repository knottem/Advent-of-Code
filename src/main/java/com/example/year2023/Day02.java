package com.example.year2023;

import com.example.template.Day;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends Day {

    public Day02() {
       super("input.txt", "02", "2023");
    }

    private static class Cube {
        String color;
        int number;

        public Cube(String color, int number) {
            this.color = color;
            this.number = number;
        }

        public String getColor() {
            return color;
        }

        public int getNumber() {
            return number;
        }
    }

    // Part 1
    //To get information, once a bag has been loaded with cubes,
    // the Elf will reach into the bag, grab a handful of random cubes,
    // show them to you, and then put them back in the bag. He'll do this a few times per game.
    //
    //You play several games and record the information from each game (your puzzle input).
    // Each game is listed with its ID number (like the 11 in Game 11: ...) followed by a
    // semicolon-separated list of subsets of cubes that were revealed from the bag
    // (like 3 red, 5 green, 4 blue).
    public long part1() {
        int validSum = 0;
        int gameNumber = 0;
        for(String line : getInput()) {
            gameNumber++;
            line = line.substring(line.indexOf(':') + 1).trim();
            String[] sets = line.split(";");
            boolean validGame = true;
            for (String set : sets) {
                String[] cubeInfo = set.split(",");
                List<Cube> cubes = new ArrayList<>();
                for (String info : cubeInfo) {
                    info = info.trim();
                    String[] parts = info.split(" ");
                    cubes.add(new Cube(parts[1], Integer.parseInt(parts[0])));
                }
                if(!(isValid(cubes))) {
                    validGame = false;
                    break;
                }
            }
            if(validGame) {
                validSum += gameNumber;
            }
        }

        return validSum;
    }

    private boolean isValid(List<Cube> cubes) {
        for (Cube cube : cubes) {
            if (cube.getColor().equals("red") && (cube.getNumber() > 12)) {
                return false;
            } else if (cube.getColor().equals("green") && (cube.getNumber() > 13)) {
                return false;
            } else if (cube.getColor().equals("blue") && (cube.getNumber() > 14)) {
                return false;
            }
        }
        return true;
    }

    // Part 2
    public long part2() {
        int validSum = 0;
        for(String line : getInput()) {
            int red, green, blue;
            red = green = blue = 0;
            line = line.substring(line.indexOf(':') + 1).trim();
            String[] sets = line.split(";");
            for (String set : sets) {
                String[] cubeInfo = set.split(",");
                for (String info : cubeInfo) {
                    String[] parts = info.trim().split(" ");
                    int number = Integer.parseInt(parts[0]);
                    if(parts[1].equals("red") && number > red) red = number;
                    if(parts[1].equals("green") && number > green) green = number;
                    if(parts[1].equals("blue") && number > blue) blue = number;
                }
            }
            validSum += red * green * blue;
        }
        return validSum;
    }

}
