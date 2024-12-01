package year2024;

import template.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 extends Day {

    public Day01() {
        super("input.txt", "01", "2024");
    }


    // Pair up all the smallest numbers and see the difference between them and add them upp
    @Override
    public long part1() {
        List<List<Integer>> lists = getLists();
        List<Integer> left = lists.get(0);
        List<Integer> right = lists.get(1);
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);
        long result = 0;
        for (int i = 0; i < left.size(); i++) {
            result += (Math.abs(left.get(i) - right.get(i)));
        }
        return result;
    }

    @Override
    public long part2() {
        List<List<Integer>> lists = getLists();
        List<Integer> left = lists.get(0);
        List<Integer> right = lists.get(1);
        Map<Integer, Integer> rightFrequency = new HashMap<>();
        for (int num : right) {
            rightFrequency.put(num, rightFrequency.getOrDefault(num, 0) + 1);
        }

        long similarityScore = 0;
        for (int num : left) {
            similarityScore += (long) num * rightFrequency.getOrDefault(num, 0);
        }

        return similarityScore;
    }

    private List<List<Integer>> getLists (){
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (String line : getInput()) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 2) {
                left.add(Integer.parseInt(parts[0]));
                right.add(Integer.parseInt(parts[1]));
            }
        }
        return new ArrayList<>(List.of(left, right));
    }
}
