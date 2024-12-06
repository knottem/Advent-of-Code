package year2024;

import template.Day;

import java.util.HashSet;
import java.util.Set;

public class Day06 extends Day {

    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public Day06() {
        super("input", "06", "2024");
    }

    //A single guard is guarding a grid marked as ^ in the grid, he's only allowed ot walk in a straight line containing .
    // If he hits a # he'll turn 90 degrees to the right, how many steps until he leaves the grid
    @Override
    public long part1() {
        int rows = getInput().size();
        int cols = getInput().get(0).length();

        //Find guard
        int x = -1, y = -1;
        for (int i = 0; i < rows; i++) {
            int j = getInput().get(i).indexOf('^');
            if (j != -1) {
                x = i;
                y = j;
                break;
            }
        }

        Set<String> visitedPositions = new HashSet<>();
        visitedPositions.add(x + "," + y);

        int direction = 0;

        // outer loop for start walking in a direction, inner for every "step"
        while (true) {
            while (true) {
                int newX = x + directions[direction][0];
                int newY = y + directions[direction][1];

                // if we are outside the grid give back the size of the set
                if (newX < 0 || newY < 0 || newX >= rows || newY >= cols) {
                    return visitedPositions.size();
                }

                // turn to right
                if (getInput().get(newX).charAt(newY) == '#') {
                    direction = (direction + 1) % 4;
                    break;
                }

                // Apply the move if it's valid
                x = newX;
                y = newY;
                visitedPositions.add(x + "," + y);
            }
        }
    }

    //WTF? I'll do this later
    //You need to get the guard stuck in a loop by adding a single new obstruction. How many different positions could you choose for this obstruction?
    @Override
    public long part2() {
        return 0;
    }
}
