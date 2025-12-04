package com.example.year2016;

import com.example.template.Day;
import com.example.utils.Pair;

import java.util.HashSet;
import java.util.Set;

public class Day01 extends Day {

    public Day01() {
        super("input.txt", "01", "2016");
    }

    static class Orientation {
        String direction = "N";
        int blocksX = 0;
        int blocksY = 0;
    }

    private void walk(Orientation o, char turnDirection, int steps) {
        switch (o.direction) {
            case "N" -> {
                o.direction = (turnDirection == 'R') ? "E" : "W";
                o.blocksX += (turnDirection == 'R') ? steps : -steps;
            }
            case "E" -> {
                o.direction = (turnDirection == 'R') ? "S" : "N";
                o.blocksY += (turnDirection == 'R') ? -steps : steps;
            }
            case "S" -> {
                o.direction = (turnDirection == 'R') ? "W" : "E";
                o.blocksX += (turnDirection == 'R') ? -steps : steps;
            }
            case "W" -> {
                o.direction = (turnDirection == 'R') ? "N" : "S";
                o.blocksY += (turnDirection == 'R') ? steps : -steps;
            }
        }
    }

    @Override
    public long part1()  {
        Orientation orientation = new Orientation();

        for (String s : getInput().get(0).trim().split(", ")) {
            char turnDirection = s.charAt(0);
            int steps = Integer.parseInt(s.substring(1));
            walk(orientation, turnDirection, steps);
        }

        return Math.abs(orientation.blocksX) + Math.abs(orientation.blocksY);
    }

    @Override
    public long part2() {
        Orientation orientation = new Orientation();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        visited.add(new Pair<>(0, 0));
        int x = 0;
        int y = 0;

        for (String s : getInput().get(0).trim().split(", ")) {
            char turnDirection = s.charAt(0);
            int steps = Integer.parseInt(s.substring(1));
            walk(orientation, turnDirection, steps);

            for (int i = 0; i < steps; i++) {
                switch (orientation.direction) {
                    case "N" -> y++;
                    case "E" -> x++;
                    case "S" -> y--;
                    case "W" -> x--;
                }

                Pair<Integer, Integer> position = new Pair<>(x, y);
                if (visited.contains(position)) {
                    return Math.abs(x) + Math.abs(y);
                } else {
                    visited.add(position);
                }
            }
        }

        return 0;
    }
}
