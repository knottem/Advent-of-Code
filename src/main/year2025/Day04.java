package year2025;

import template.Day;

import static utils.GridUtils.*;


public class Day04 extends Day {

    public Day04(){
        super("input", "04", "2025");
    }

    @Override
    public long part1() {
        char[][] grid = buildGrid(getInput());
        long total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '.') continue;
                if(countNeighbors(grid, i, j, '@') < 4){
                    total++;
                }
            }
        }
        return total;
    }

    @Override
    public long part2() {
        char[][] grid = buildGrid(getInput());
        long total = 0;
        while (true) {
            long temp = total;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '.') continue;
                    if (countNeighbors(grid, i, j, '@') < 4) {
                        grid[i][j] = '.';
                        total++;
                    }
                }
            }
            if (temp == total) break;
        }
       return total;
    }

}
