package com.example.year2024;

import com.example.template.Day;

import java.util.Stack;

import static com.example.utils.GridUtils.buildGrid;


public class Day12 extends Day {

    char[][] grid;

    public Day12() {
        super("12", "2024");
        grid = buildGrid(getInput());
    }

    @Override
    public long part1() {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        long totalPrice = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                // if we haven't visited this plot yet, it's a new region
                if (!visited[row][col]) {
                    totalPrice += calculateRegion(visited, row, col);
                }

            }
        }
        return totalPrice;
    }

    @Override
    public long part2() {
        return 0;
    }

    private long calculateRegion(boolean[][] visited, int startRow, int startCol) {

        char targetChar = grid[startRow][startCol];

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        long area = 0; // Count of plots
        long perimeter = 0; // Count of external edges

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
        return area * perimeter;
    }
}
