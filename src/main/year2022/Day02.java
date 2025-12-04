package year2022;

import template.Day;

public class Day02 extends Day {

    public Day02() {
        super("input.txt", "02", "2022");
    }

    private int gameOfRps(String text) {
        int sum = 0;
        if (text.startsWith("A")) { // rock
            if (text.endsWith("Y")) {
                sum += 2 + 6;
            } else if (text.endsWith("X")) {
                sum += 1 + 3;
            } else if (text.endsWith("Z")) {
                sum += 3;
            }
        } else if (text.startsWith("B")) { // paper
            if (text.endsWith("Y")) {
                sum += 2 + 3;
            } else if (text.endsWith("X")) {
                sum += 1 + 0;
            } else if (text.endsWith("Z")) {
                sum += 3 + 6;
            }
        } else if (text.startsWith("C")) { // scissors
            if (text.endsWith("Y")) {
                sum += 2 + 0;
            } else if (text.endsWith("X")) {
                sum += 1 + 6;
            } else if (text.endsWith("Z")) {
                sum += 3 + 3;
            }
        }
        return sum;
    }

    private int riggedGameOfRps(String text) {
        int sum = 0;
        if (text.startsWith("A")) { // rock
            if (text.endsWith("Y")) {
                sum += 1 + 3;
            } else if (text.endsWith("X")) {
                sum += 3 + 0;
            } else if (text.endsWith("Z")) {
                sum += 2 + 6;
            }
        } else if (text.startsWith("B")) { // paper
            if (text.endsWith("Y")) {
                sum += 2 + 3;
            } else if (text.endsWith("X")) {
                sum += 1 + 0;
            } else if (text.endsWith("Z")) {
                sum += 3 + 6;
            }
        } else if (text.startsWith("C")) { // scissors
            if (text.endsWith("Y")) {
                sum += 3 + 3;
            } else if (text.endsWith("X")) {
                sum += 2 + 0;
            } else if (text.endsWith("Z")) {
                sum += 1 + 6;
            }
        }
        return sum;
    }

    @Override
    public long part1() {
        int sum = 0;
        for (String line : getInput()) {
            sum += gameOfRps(line);
        }
        return sum;
    }

    @Override
    public long part2() {
        int sum = 0;
        for (String line : getInput()) {
            sum += riggedGameOfRps(line);
        }
        return sum;
    }

}
