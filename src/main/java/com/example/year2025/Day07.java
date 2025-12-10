package com.example.year2025;

import com.example.template.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 extends Day {

    public Day07() {
        super("07", "2025");
    }

    @Override
    public long part1() {
        List<String> newInput = getInput();
        Map<Integer, Character> savedUpdates = new HashMap<>();
        Map<Integer, Character> newUpdates = new HashMap<>();
        long total = 0;
        for (int i = 0; i < newInput.size(); i++) {
            String s = newInput.get(i);
            newUpdates.clear();
            for (int j = 0; j < s.length(); j++) {
                if (savedUpdates.containsKey(j) && s.charAt(j) == '.' && savedUpdates.get(j) == '|') {
                    s = s.substring(0, j) + '|' + s.substring(j + 1);
                }
                if (s.charAt(j) == 'S') {
                    newUpdates.put(j, '|');
                }
                if (s.charAt(j) == '|') {
                    newUpdates.put(j, '|');
                }
                if (s.charAt(j) == '^' && savedUpdates.getOrDefault(j, '.') == '|') {
                    boolean didSplit = false;
                    if (j - 1 >= 0 && s.charAt(j - 1) == '.') {
                        s = s.substring(0, j - 1) + '|' + s.substring(j);
                        newUpdates.put(j - 1, '|');
                        didSplit = true;
                    }
                    if (j + 1 < s.length() && s.charAt(j + 1) == '.') {
                        s = s.substring(0, j + 1) + '|' + s.substring(j + 2);
                        newUpdates.put(j + 1, '|');
                        didSplit = true;
                    }
                    if (didSplit) {
                        total++;
                    }
                }
            }
            savedUpdates = new HashMap<>(newUpdates);
            newInput.set(i, s);
            /*
            System.out.println("---------------");
            newInput.forEach(System.out::println);
            System.out.println("---------------\n\n");

             */
        }
        return total;
    }

    public long part2() {
        List<String> grid = getInput();
        int height = grid.size();
        int width = grid.getFirst().length();
        int startRow = -1;
        int startCol = -1;
        for (int r = 0; r < height; r++) {
            int c = grid.get(r).indexOf('S');
            if (c != -1) {
                startRow = r;
                startCol = c;
                break;
            }
        }
        long[] curr = new long[width];
        curr[startCol] = 1L;
        for (int r = startRow; r < height - 1; r++) {
            long[] next = new long[width];
            String below = grid.get(r + 1);
            for (int j = 0; j < width; j++) {
                long ways = curr[j];
                if (ways == 0) continue;
                char cellBelow = below.charAt(j);
                if (cellBelow == '^') {
                    if (j - 1 >= 0)
                        next[j - 1] += ways;
                    if (j + 1 < width)
                        next[j + 1] += ways;
                } else {
                    next[j] += ways;
                }
            }
            curr = next;
        }

        long totalTimelines = 0;
        for (long v : curr) totalTimelines += v;

        return totalTimelines;
    }
}
