package year2023;

import template.Day;

import java.util.*;

public class Day15 extends Day {

    public Day15(){
        super("input.txt", "15", "2023");
    }

    String[] asciiResults = getInput().get(0).split(",");

    private int calculateValue(String input){
        int value = 0;
        for (int i = 0; i < input.length(); i++) {
            value = ((input.charAt(i) + value) * 17) % 256;
        }
        return value;
    }

    @Override
    public long part1() {
        int sum = 0;
        for (String asciiResult : asciiResults) {
            sum += calculateValue(asciiResult);
        }
        return sum;
    }

    @Override
    public long part2() {
        List<LinkedHashMap<String, Integer>> box = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            box.add(new LinkedHashMap<>());
        }
        for (String asciiResult : asciiResults) {
            String[] temp = asciiResult.split("(?<=[=-])|(?=[=-])");
            int value = calculateValue(temp[0]);
            if (temp.length == 2) {
                box.get(value).remove(temp[0]);
            } else {
                box.get(value).put(temp[0], Integer.valueOf(temp[2]));
            }
        }

        int sum = 0;
        for (int i = 0; i < box.size(); i++) {
            int mapOrder = 1;
            for (Map.Entry<String, Integer> entry : box.get(i).entrySet()) {
                sum += (i+1) * mapOrder * entry.getValue();
                mapOrder++;
            }
        }
        return sum;
    }
}
