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
        return totalJoltage(2);
    }

    @Override
    public long part2() {
        return totalJoltage(12);
    }


    public long totalJoltage(int amount) {
        long total = 0;
        for (List<Integer> list : convertedList) {
            int startIndex = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < amount; i++) {
                int max = -1;
                int maxIndex = startIndex;
                int lastSearchIndex = list.size() - (amount - i);
                for (int j = startIndex; j <= lastSearchIndex; j++) {
                    if (list.get(j) > max) {
                        max = list.get(j);
                        maxIndex = j;
                    }
                }
                sb.append(max);
                startIndex = maxIndex + 1;
            }
            total += Long.parseLong(sb.toString());
        }

        return total;
    }
}
