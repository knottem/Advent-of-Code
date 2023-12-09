package year2020;

import template.Day;

import java.util.List;

public class Day01 extends Day {


    public Day01() {
        super("input.txt", "01", "2020");
    }

    private List<Integer> getInputAsInt()  {
        List<String> lines = getInput();
        return lines.stream().map(Integer::parseInt).toList();
    }

    @Override
    public long part1() {
        List<Integer> input = getInputAsInt();
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                if (input.get(i) + input.get(j) == 2020) {
                    return (long) input.get(i) * input.get(j);
                }
            }
        }
        throw new IllegalStateException("No two numbers found that add up to 2020");
    }

    public long part1V2() {
        List<Integer> input = getInputAsInt();
        for (int number : input) {
            int complement = 2020 - number;
            if (input.contains(complement)) {
                return (long) number * complement;
            }
        }
        throw new IllegalStateException("No two numbers found that add up to 2020");
    }

    @Override
    public long part2() {
        List<Integer> input = getInputAsInt();
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                for (int k = j + 1; k < input.size(); k++) {
                    if (input.get(i) + input.get(j) + input.get(k) == 2020) {
                        return (long) input.get(i) * input.get(j) * input.get(k);
                    }
                }
            }
        }
        throw new IllegalStateException("No three numbers found that add up to 2020");
    }

}
