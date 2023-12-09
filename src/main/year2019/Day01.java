package year2019;

import template.Day;

import java.util.List;

public class Day01 extends Day {

    public Day01() {
        super("input.txt", "01", "2019");
    }

    private List<Integer> getInputAsInt() {
        return getInput().stream().map(Integer::parseInt).toList();
    }

    @Override
    public long part1() {
        return getInputAsInt().stream().mapToInt(value -> value / 3 - 2).sum();
    }

    @Override
    public long part2() {
        return getInputAsInt().stream().mapToInt(this::fuel).sum();
    }

    private int fuel(int mass) {
        int fuelNeeded = mass / 3 - 2;
        return fuelNeeded > 0 ? fuelNeeded + fuel(fuelNeeded) : 0;
    }

}
