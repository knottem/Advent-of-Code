package year2025;

import template.Day;

import java.util.ArrayList;
import java.util.List;

public class Day03 extends Day {

    List<List<Integer>> convertedList = new ArrayList<>();

    public Day03(){
        super("input", "03", "2025");
        for (String line : getInput()) {
            convertedList.add(line.chars().mapToObj(Character::getNumericValue).toList());
        }
    }

    @Override
    public long part1() {
        long total = 0;
        for (List<Integer> list : convertedList) {
            int max = 0;
            int maxIndex = 0;
            int secondMax = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > max) {
                    max = list.get(i);
                    maxIndex = i;
                }
            }
            for (int i = maxIndex + 1; i < list.size() ; i++) {
                if (list.get(i) > secondMax) {
                    secondMax = list.get(i);
                }
            }
            String s1 = String.valueOf(max);
            String s2 = String.valueOf(secondMax);
            total += Integer.parseInt(s1+s2);
        }
        return total;
    }

    @Override
    public long part2() {
        return 0;
    }
}
