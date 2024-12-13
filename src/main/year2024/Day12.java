package year2024;

import template.Day;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Day12 extends Day {

    public Day12() {
        super("input", "12", "2024");
    }

    @Override
    public long part1() {

        int rows = getInput().size();
        int cols = getInput().get(0).length();

        char[][] grid = buildGrid(rows, cols);
        boolean[][] visited = new boolean[rows][cols];

        Map<Character, Integer> totalCost = new HashMap<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!visited[row][col]) {
                    char currentChar = grid[row][col];

                    // Calculate area and perimeter for this region
                    int[] regionStats = calculateRegion(grid, visited, row, col, currentChar);
                    int area = regionStats[0];
                    int perimeter = regionStats[1];
                    int cost = area * perimeter;

                    // Accumulate the total cost for this character
                    totalCost.put(currentChar, totalCost.getOrDefault(currentChar, 0) + cost);
                }
            }
        }

        // Compute and print the total cost for all regions
        long totalPrice = 0;
        for (Map.Entry<Character, Integer> entry : totalCost.entrySet()) {
            totalPrice += entry.getValue();
        }
        return totalPrice;
    }

    @Override
    public long part2() {
        return 0;
    }

    private char[][] buildGrid(int rows, int cols) {
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = getInput().get(i).toCharArray();
        }
        return grid;
    }

    private static int[] calculateRegion(char[][] grid, boolean[][] visited, int startRow, int startCol,
                                         char targetChar) {

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        int area = 0; // Count of plots
        int perimeter = 0; // Count of external edges

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int row = current[0];
            int col = current[1];
            area++;

            // check all 4 directions
            for (int i = 0; i < 4; i++) {
                int newRow = row + dRow[i];
                int newCol = col + dCol[i];

                // Check bounds
                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) {
                    perimeter++; // Out of bounds counts, took forever to figure this out
                } else if (grid[newRow][newCol] != targetChar) {
                    perimeter++; // Neighboring a different region counts
                } else if (!visited[newRow][newCol]) {
                    stack.push(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return new int[]{area, perimeter};
    }
}
