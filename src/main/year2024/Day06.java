package year2024;

import template.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 extends Day {

    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int rows;
    int cols;
    List<Integer> guard;


    public Day06() {
        super("input", "06", "2024");
        rows = getInput().size();
        cols = getInput().get(0).length();
        guard = getGuardPositions();
    }

    //A single guard is guarding a grid marked as ^ in the grid, he's only allowed ot walk in a straight line containing .
    // If he hits a # he'll turn 90 degrees to the right, how many steps until he leaves the grid
    @Override
    public long part1() {
        int guardX = guard.get(0), guardY = guard.get(1);
        Set<String> visitedPositions = new HashSet<>();
        visitedPositions.add(guardX + "," + guardY);

        int direction = 0;

        while (true) {
            int newX = guardX + directions[direction][0];
            int newY = guardY + directions[direction][1];

            if (newX < 0 || newY < 0 || newX >= rows || newY >= cols) {
                return visitedPositions.size();
            }

            // Check if the guard hits a wall
            if (getInput().get(newX).charAt(newY) == '#') {
                direction = (direction + 1) % 4;
            } else {
                guardX = newX;
                guardY = newY;
                visitedPositions.add(guardX + "," + guardY);
            }
        }
    }

    //You need to get the guard stuck in a loop by adding a single new obstruction. How many different positions could you choose for this obstruction?
    // Bruteforce , kinda slow.
    @Override
    public long part2() {
        long loopCounter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (getInput().get(i).charAt(j) != '.') {
                    continue;
                }

                // Simulate placing an obstruction at (i, j)
                if (isLoopingWithObstruction(i, j)) {
                    //System.out.println("Found loop: " + loopCounter);
                    loopCounter++;
                }
            }
        }

        return loopCounter;
    }

    private List<Integer> getGuardPositions() {
        List<Integer> guardPositions = new ArrayList<>();
        int x = -1, y = -1;
        for (int i = 0; i < rows; i++) {
            int j = getInput().get(i).indexOf('^');
            if (j != -1) {
                guardPositions.add(i);
                guardPositions.add(j);
                break;
            }
        }
        return guardPositions;
    }

    //Bruteforce, not really a good solution
    private boolean isLoopingWithObstruction(int obsX, int obsY) {
        List<String> modifiedGrid = new ArrayList<>(getInput());
        StringBuilder row = new StringBuilder(modifiedGrid.get(obsX));
        row.setCharAt(obsY, '#');
        modifiedGrid.set(obsX, row.toString());

        int guardX = guard.get(0), guardY = guard.get(1);
        Set<String> visitedStates = new HashSet<>();
        int direction = 0;

        // Loop until we detect a loop or exits the grid
        while (true) {
            String state = guardX + "," + guardY + "," + direction;
            if (visitedStates.contains(state)) {
                return true;
            }
            visitedStates.add(state);

            int newX = guardX + directions[direction][0];
            int newY = guardY + directions[direction][1];

            if (newX < 0 || newY < 0 || newX >= rows || newY >= cols) {
                return false;
            }

            if (modifiedGrid.get(newX).charAt(newY) == '#') {
                direction = (direction + 1) % 4;
            } else {
                guardX = newX;
                guardY = newY;
            }
        }
    }
}
