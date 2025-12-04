package year2016;

import template.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Day02  extends Day {

    public Day02() {
        super("input.txt", "02", "2016");
    }

    static int[][] key = {
            { 1, 2, 3},
            { 4, 5, 6},
            { 7, 8, 9}};
    // 10 = A, 11 = B, 12 = C, 13 = D
    static int[][] key2 = {
            { 0, 0, 1, 0, 0},
            { 0, 2, 3, 4, 0},
            { 5, 6, 7, 8, 9},
            { 0, 10, 11, 12, 0},
            { 0, 0, 13, 0, 0}};

    @Override
    public long part1() {
        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(1);
        coordinates.add(1);
        AtomicReference<String> result = new AtomicReference<>("");
        getInput().forEach(s -> {
                    for (int i = 0; i < s.length(); i++) {
                        switch (s.charAt(i)) {
                            case 'U' -> {
                                if (coordinates.get(1) > 0) {
                                    coordinates.set(1, coordinates.get(1) - 1);
                                }
                            }
                            case 'D' -> {
                                if (coordinates.get(1) < 2) {
                                    coordinates.set(1, coordinates.get(1) + 1);
                                }
                            }
                            case 'L' -> {
                                if (coordinates.get(0) > 0) {
                                    coordinates.set(0, coordinates.get(0) - 1);
                                }
                            }
                            case 'R' -> {
                                if (coordinates.get(0) < 2) {
                                    coordinates.set(0, coordinates.get(0) + 1);
                                }
                            }
                        }
                    }
                    result.updateAndGet(v -> v + key[coordinates.get(1)][coordinates.get(0)]);
                });
        System.out.println("Part1: " + result.get());
        return 0;
    }

    @Override
    public long part2(){
        int x = 0;
        int y = 2;
        StringBuilder result = new StringBuilder();
        for (String s : getInput()) {
            for (char c : s.toCharArray()) {
                switch (c) {
                    case 'U' -> y = (y > 0 && key2[y - 1][x] != 0) ? y - 1 : y;
                    case 'D' -> y = (y < 4 && key2[y + 1][x] != 0) ? y + 1 : y;
                    case 'L' -> x = (x > 0 && key2[y][x - 1] != 0) ? x - 1 : x;
                    case 'R' -> x = (x < 4 && key2[y][x + 1] != 0) ? x + 1 : x;
                }
            }
            int code = key2[y][x];
            if (code == 10) result.append('A');
            else if (code == 11) result.append('B');
            else if (code == 12) result.append('C');
            else if (code == 13) result.append('D');
            else result.append(code);
        }
        System.out.println("Part2: " + result);
        return 0;

    }

}
