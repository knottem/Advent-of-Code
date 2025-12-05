package com.example.year2024;

import com.example.template.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 extends Day {

    private final List<List<Integer>> list;

    public Day01() {
        super("01", "2024");
        if(isInputAvailable()){
            list = getLists();
        } else {
            list = new ArrayList<>();
        }
    }


    // Pair up all the smallest numbers and see the difference between them and add them upp
    @Override
    public long part1() {
        List<Integer> left = list.get(0);
        List<Integer> right = list.get(1);
        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);
        long result = 0;
        for (int i = 0; i < left.size(); i++) {
            result += (Math.abs(left.get(i) - right.get(i)));
        }
        return result;
    }


    // Calculate the similarity score by checking how often each number from the left list appears in the right list.
    // Multiply each number in the left list by its frequency in the right list and sum up the results.
    @Override
    public long part2() {
        List<Integer> left = list.get(0);
        List<Integer> right = list.get(1);
        Map<Long, Integer> rightFrequency = new HashMap<>();
        for (long num : right) {
            rightFrequency.put(num, rightFrequency.getOrDefault(num, 0) + 1);
        }

        long result = 0;
        for (long num : left) {
            result += num * rightFrequency.getOrDefault(num, 0);
        }
        return result;
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
