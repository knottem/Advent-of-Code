package utils;

import java.util.List;

public class GridUtils {

    private GridUtils() {}

    public static char[][] buildGrid(List<String> input) {
        int rows = input.size();
        int cols = input.get(0).length();
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = input.get(i).toCharArray();
        }
        return grid;
    }

    public static boolean isInBounds(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

    public static int countNeighbors(char[][] grid, int row, int col, char target) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1,  0,  1, -1, 1, -1, 0, 1};

        for (int k = 0; k < 8; k++) {
            int nr = row + dr[k];
            int nc = col + dc[k];

            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                if (grid[nr][nc] == target) {
                    count++;
                }
            }
        }
        return count;
    }
}
