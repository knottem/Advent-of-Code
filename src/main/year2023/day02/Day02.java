package year2023.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day02 {

    private List<String> input;

    public Day02(String text) {
        String day = "02"; //day
        String year = "2023"; //year
        try{
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Cube {
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
    public int part1() {
        int validSum = 0;
        int gameNumber = 0;
        for(String line : input) {
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

    public int part2() {
        int validSum = 0;
        for(String line : input) {
            line = line.substring(line.indexOf(':') + 1).trim();
            String[] sets = line.split(";");
            List<Cube> cubes = new ArrayList<>();
            for (String set : sets) {
                String[] cubeInfo = set.split(",");
                for (String info : cubeInfo) {
                    info = info.trim();
                    String[] parts = info.split(" ");
                    int number = Integer.parseInt(parts[0]);
                    boolean exists = false;
                    for (Cube cube : cubes) {
                        if(cube.getColor().equals(parts[1])) {
                            exists = true;
                            if(cube.getNumber() < number) {
                                cube.number = number;
                            }
                        }
                    }
                    if(!exists) {
                        cubes.add(new Cube(parts[1], Integer.parseInt(parts[0])));
                    }
                }
            }
            int sum = 1;
            for(Cube cube : cubes) {
                sum *= cube.getNumber();
            }
            validSum += sum;
        }
        return validSum;
    }

}
