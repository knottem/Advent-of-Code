package com.example.year2024;

import com.example.template.Day;

public class Day04 extends Day {

    public Day04() {
        super("04", "2024");
    }

    private final int[][] DIRECTIONS = {
            {0, 1},   // Right
            {0, -1},  // Left
            {1, 0},   // Down
            {-1, 0},  // Up
            {1, 1},   // Diagonal Down-Right
            {-1, -1}, // Diagonal Up-Left
            {1, -1},  // Diagonal Down-Left
            {-1, 1}   // Diagonal Up-Right
    };

    // Find the amount of XMAS in the grid, it can be horizontal, vertical, diagonal, written backwards,
    // or even overlapping other words.
    @Override
    public long part1() {
        int rows = getInput().size();
        int cols = getInput().get(0).length();
        long result = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] direction : DIRECTIONS) {
                    if(matches(rows, cols , row, col, direction[0], direction[1])) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private boolean matches(int rows, int cols, int row, int col, int rowDelta, int colDelta) {
        String target = "XMAS";
        for (int i = 0; i < target.length(); i++) {
            int newRow = row + i * rowDelta;
            int newCol = col + i * colDelta;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols ||
                    getInput().get(newRow).charAt(newCol) != target.charAt(i)) {
                return false;
            }
        }

        return true;
    }


    // Find two MAS in the shape of an X instead
    @Override
    public long part2() {
        int rows = getInput().size();
        int cols = getInput().get(0).length();
        long count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (isXMASCross(row, col)) {
                    count++;
                }
            }
        }

        return count;
    }


    // Check if there are two "MAS" / "SAM strings forming an X pattern
    // only need to check from any A, since that's always the middle of the cross
    private boolean isXMASCross(int row, int col) {
        if (getInput().get(row).charAt(col) != 'A') {
            return false;
        }

        boolean topLeftToBottomRight = checkExactDiagonal(row, col, DIRECTIONS[5], DIRECTIONS[4]);
        boolean topRightToBottomLeft = checkExactDiagonal(row, col, DIRECTIONS[7], DIRECTIONS[6]);
        boolean bottomLeftToTopRight = checkExactDiagonal(row, col, DIRECTIONS[6], DIRECTIONS[7]);
        boolean bottomRightToTopLeft = checkExactDiagonal(row, col, DIRECTIONS[4], DIRECTIONS[5]);

        return (topLeftToBottomRight && topRightToBottomLeft) || (bottomLeftToTopRight && bottomRightToTopLeft);
    }

    private boolean checkExactDiagonal(int row, int col, int[] direction1, int[] direction2) {
        int mRow = row + direction1[0];
        int mCol = col + direction1[1];
        int sRow = row + direction2[0];
        int sCol = col + direction2[1];
        boolean isMAS = isValid(mRow, mCol, 'M') && isValid(sRow, sCol, 'S');
        boolean isSAM = isValid(mRow, mCol, 'S') && isValid(sRow, sCol, 'M');

        return isMAS || isSAM;
    }

    private boolean isValid(int row, int col, char expected) {
        int rows = getInput().size();
        int cols = getInput().get(0).length();

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }

        return getInput().get(row).charAt(col) == expected;
    }

}

