package com.example.year2023;

import com.example.template.Day;

public class Day10 extends Day {

    public Day10() {
        super("input.txt", "10", "2023");
    }

    public Day10(String test) {
        super(test, "10", "2023");
    }

    private int[][] getPipesMap() {
        int[][] map = new int[getInput().size()][getInput().size()];
        for (int i = 0; i < getInput().size(); i++) {
            char[] lines = getInput().get(i).toCharArray();
            for (int j = 0; j < lines.length; j++) {
                map[i][j] = getPipe(lines[j]);
            }
        }
        return map;
    }



    private int getPipe(char c) {
        return switch (c) {
            case '|' -> 0; //is a vertical pipe connecting north and south.
            case '-' -> 1; //is a horizontal pipe connecting east and west.
            case 'L' -> 2; //is a bend pipe connecting north and east.
            case 'J' -> 3; //is a bend pipe connecting north and west.
            case '7' -> 4; //is a bend pipe connecting south and west.
            case 'F' -> 5; //is a bend pipe connecting south and east.
            case '.' -> 6; //is an empty space.
            case 'S' -> 7; //is the starting point.
            default -> c;
        };
    }

    private boolean isValidConnectingPipe(int currentPipe, int nextPipe, String direction) {
        switch (direction) {
            case "up" -> {
                if(currentPipe == 1 || currentPipe == 4 || currentPipe == 5 || nextPipe == 6){
                    return false;
                }
                return nextPipe == 0 || nextPipe == 4 || nextPipe == 5;
            }
            case "down" -> {
                if(currentPipe == 1 || currentPipe == 2 || currentPipe == 3 || nextPipe == 6){
                    return false;
                }
                return nextPipe == 0 || nextPipe == 2 || nextPipe == 3;
            }
            case "left" -> {
                if(currentPipe == 0 || currentPipe == 2 || currentPipe == 5 || nextPipe == 6){
                    return false;
                }
                return nextPipe == 1 || nextPipe == 2 || nextPipe == 5;
            }
            case "right" -> {
                if(currentPipe == 0 || currentPipe == 3 || currentPipe == 4 || nextPipe == 6){
                    return false;
                }
                return nextPipe == 1 || nextPipe == 3 || nextPipe == 4;
            }
            default -> {
                return false;
            }
        }
    }

    private String getDirection(int directionIndex) {
        return switch (directionIndex) {
            case 0 -> "up";
            case 1 -> "down";
            case 2 -> "left";
            case 3 -> "right";
            default -> "invalid";
        };
    }

    private int getStartingPointShape(int[][] pipesMap, int x, int y) {
        for (int i = 0; i < 6; i++) {
            boolean isVerticalUp = (x > 0) && isValidConnectingPipe(pipesMap[x][y],pipesMap[x - 1][y], "up");
            boolean isVerticalDown = (x < pipesMap.length - 1) && isValidConnectingPipe(pipesMap[x][y],pipesMap[x + 1][y], "down");
            boolean isHorizontalLeft = (y > 0) && isValidConnectingPipe(pipesMap[x][y],pipesMap[x][y - 1], "left");
            boolean isHorizontalRight = (y < pipesMap[0].length - 1) && isValidConnectingPipe(pipesMap[x][y],pipesMap[x][y + 1], "right");
            if (isVerticalUp && isVerticalDown && isHorizontalLeft && isHorizontalRight) return 7;
            else if (isVerticalUp && isVerticalDown) return 0;
            else if (isHorizontalLeft && isHorizontalRight) return 1;
            else if (isVerticalUp && isHorizontalRight) return 2;
            else if (isVerticalUp && isHorizontalLeft) return 3;
            else if (isVerticalDown && isHorizontalLeft) return 4;
            else if (isVerticalDown && isHorizontalRight) return 5;
        }
        return -1;
    }


    @Override
    public long part1() {
        int[][] pipesMap = getPipesMap();
        int startX = -1;
        int startY = -1;
        int numRows = pipesMap.length;
        int numCols = pipesMap[0].length;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (pipesMap[i][j] == 7) {
                    startX = i;
                    startY = j;
                    break;
                }
            }
        }
        pipesMap[startX][startY] = getStartingPointShape(pipesMap, startX, startY);
        int maxSteps = 0;
        int currentX = startX;
        int currentY = startY;

        boolean[][] visited = new boolean[numRows][numCols];
        visited[startX][startY] = true;
        while (true) {
            // Try to move in each direction (up, down, left, right)
            int[] dx = { -1, 1, 0, 0 };
            int[] dy = { 0, 0, -1, 1 };
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                int newX = currentX + dx[i];
                int newY = currentY + dy[i];
                if (newX >= 0 && newX < numRows && newY >= 0 && newY < numCols && !visited[newX][newY]) {
                    if (isValidConnectingPipe(pipesMap[currentX][currentY],pipesMap[newX][newY], getDirection(i))) {
                        visited[newX][newY] = true;
                        currentX = newX;
                        currentY = newY;
                        maxSteps++;
                        moved = true;
                        break;
                    }
                }
            }
            if (!moved) {
                break;
            }
        }

        return (maxSteps + 1) / 2;
    }

    @Override
    public long part2() {
        return -1;
    }

}
