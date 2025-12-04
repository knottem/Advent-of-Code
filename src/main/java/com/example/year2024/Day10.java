package com.example.year2024;

import com.example.template.Day;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day10 extends Day {

    List<List<Long>> map;

    public Day10() {
        super("input", "10", "2024");
        map = convertToLongListListGrid(getInput());
    }


    // Part 1: Find the sum of reachable peaks
    // - Iterate over the topographic map to identify all trailheads (points with elevation 0).
    // - For each trailhead, perform a depth-first search (DFS) to find all peaks (points with elevation 9) reachable
    //   while strictly following the rule that the elevation must increase by 1 at each step.
    // - Sum up the total number of reachable peaks from all trailheads and return the result.
    @Override
    public long part1() {
        return findReachable(map, false);
    }

    // Part 2: Calculate the sum of trailhead ratings
    // - Iterate over the topographic map to identify all trailheads (points with elevation 0).
    // - For each trailhead, perform a depth-first search (DFS) to identify all distinct trails (unique paths)
    //   leading to peaks (points with elevation 9). Each unique trail is characterized by its sequence of visited points.
    // - Calculate the rating for each trailhead as the number of unique trails originating from it.
    // - Sum up the ratings for all trailheads and return the result.
    @Override
    public long part2() {
        return findReachable(map, true);
    }

    private List<List<Long>> convertToLongListListGrid(List<String> mapRows) {
        List<List<Long>> grid = new ArrayList<>();
        for (String row : mapRows) {
            List<Long> longRow = new ArrayList<>();
            for (char ch : row.toCharArray()) {
                longRow.add((long) Character.getNumericValue(ch));
            }
            grid.add(longRow);
        }
        return grid;
    }

    private long findReachable(List<List<Long>> map, boolean part2) {
        long result = 0;

        for (int r = 0; r < map.size(); r++) {
            for (int c = 0; c < map.get(r).size(); c++) {
                if (map.get(r).get(c) == 0L) {
                    result += part2
                            ? calculateTrailheadRating(map, r, c)
                            : countReachableTops(map, r, c);
                }
            }
        }

        return result;
    }


    private int countReachableTops(List<List<Long>> map, int startRow, int startCol) {
        boolean[][] visited = new boolean[map.size()][map.get(0).size()];
        Set<Point> completedPeaks = new HashSet<>();
        return dfs(map, startRow, startCol, visited, completedPeaks, -1L);
    }

    private int calculateTrailheadRating(List<List<Long>> map, int startRow, int startCol) {
        boolean[][] visited = new boolean[map.size()][map.get(0).size()];
        Set<Set<Point>> uniqueTrails = new HashSet<>();
        dfsForTrails(map, startRow, startCol, visited, new HashSet<>(), uniqueTrails, -1L);
        return uniqueTrails.size();
    }

    private int dfs(List<List<Long>> map, int row, int col, boolean[][] visited, Set<Point> completedPeaks, long currentElevation) {
        if (isInvalidMove(map, row, col, visited, currentElevation)) {
            return 0;
        }

        Point current = new Point(row, col);
        long elevation = map.get(row).get(col);

        // If at a peak, count it
        if (elevation  == 9L) {
            if (!completedPeaks.add(current)) {
                return 0; // already counted
            }
            return 1;
        }

        // Mark this cell as visited
        visited[row][col] = true;

        // Explore (up, down, left, right)
        int count = dfs(map, row - 1, col, visited, completedPeaks, elevation) +
                dfs(map, row + 1, col, visited, completedPeaks, elevation) +
                dfs(map, row, col - 1, visited, completedPeaks, elevation) +
                dfs(map, row, col + 1, visited, completedPeaks, elevation);

        // Backtrack
        visited[row][col] = false;

        return count;
    }

    private void dfsForTrails(List<List<Long>> map, int row, int col, boolean[][] visited, Set<Point> currentTrail, Set<Set<Point>> uniqueTrails, long currentElevation) {
        if (isInvalidMove(map, row, col, visited, currentElevation)) {
            return;
        }

        Point current = new Point(row, col);
        long elevation = map.get(row).get(col);

        // If we reach a peak (9), record the current trail
        if (elevation == 9L) {
            Set<Point> trailSnapshot = new HashSet<>(currentTrail); // Copy current trail
            trailSnapshot.add(current); // Add the peak
            uniqueTrails.add(trailSnapshot); // Add to unique trails
            return;
        }

        // Mark this cell as visited
        visited[row][col] = true;
        currentTrail.add(current);

        // Explore (up, down, left, right)
        dfsForTrails(map, row - 1, col, visited, currentTrail, uniqueTrails, elevation);
        dfsForTrails(map, row + 1, col, visited, currentTrail, uniqueTrails, elevation);
        dfsForTrails(map, row, col - 1, visited, currentTrail, uniqueTrails, elevation);
        dfsForTrails(map, row, col + 1, visited, currentTrail, uniqueTrails, elevation);

        // Backtrack
        visited[row][col] = false;
        currentTrail.remove(current);
    }

    private boolean isInvalidMove(List<List<Long>> map, int row, int col, boolean[][] visited, long currentElevation) {
        if (row < 0 || col < 0 || row >= map.size() || col >= map.get(0).size() || visited[row][col]) {
            return true;
        }
        long elevation = map.get(row).get(col);
        return (currentElevation != -1 && elevation != currentElevation + 1) || (currentElevation == -1 && elevation != 0L);
    }
}
