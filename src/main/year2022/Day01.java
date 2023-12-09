package year2022;

import template.Day;

import java.util.ArrayList;
import java.util.List;

public class Day01 extends Day {

    public Day01() {
        super("input.txt", "01", "2022");
    }

    private List<List<Integer>> getInputAsList() {
        List<List<Integer>> inputList = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();

        for (String line : getInput()) {
            if (line.isBlank()) {
                if (!currentList.isEmpty()) {
                    inputList.add(currentList);
                    currentList = new ArrayList<>();
                }
            } else {
                String[] numbers = line.split(" ");
                for (String number : numbers) {
                    currentList.add(Integer.parseInt(number));
                }
            }
        }

        if (!currentList.isEmpty()) {
            inputList.add(currentList);
        }

        return inputList;
    }

    @Override
    public long part1() {
        List<Integer> test2 = new ArrayList<>();
        List<List<Integer>> inputList = getInputAsList();

        for (List<Integer> list : inputList) {
            int sum = 0;
            for (Integer num : list) {
                sum += num;
            }
            test2.add(sum);
        }

        return test2.stream().max(Integer::compareTo).orElse(0);
    }

    @Override
    public long part2() {
        List<Integer> test2 = new ArrayList<>();
        List<List<Integer>> inputList = getInputAsList();

        for (List<Integer> list : inputList) {
            int sum = 0;
            for (Integer num : list) {
                sum += num;
            }
            test2.add(sum);
        }

        test2.sort((a, b) -> b - a);
        int sum = 0;
        for (int i = 0; i < Math.min(3, test2.size()); i++) {
            sum += test2.get(i);
        }

        return sum;
    }
}
