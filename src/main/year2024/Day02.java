package year2024;

import template.Day;

import java.util.ArrayList;
import java.util.List;

import static utils.ParsingUtils.parseNumbersWithSpaces;

public class Day02 extends Day {

    public Day02() {
        super("input", "02", "2024");
    }


    // The levels are either all increasing or all decreasing
    // Any two adjacent levels differ by at least one and at most three.
    @Override
    public long part1() {
        List<String> input = getInput();
        long correct = 0;
        for (String s : input) {
            List<Long> numbers = parseNumbersWithSpaces(s);
            if (isValidSequence(numbers)) {
                correct++;
            }
        }
        return correct;
    }

    // as part 1 but we also can remove one level(number) to check if it's safe
    @Override
    public long part2() {
        List<String> input = getInput();
        long correct = 0;
        for (String s : input) {
            List<Long> numbers = parseNumbersWithSpaces(s);
            if (isValidSequence(numbers)) {
                correct++;
                continue;
            }

            for (int i = 0; i < numbers.size(); i++) {
                List<Long> changed = new ArrayList<>(numbers);
                changed.remove(i);
                if(isValidSequence(changed)) {
                    correct++;
                    break;
                }
            }
        }
        return correct;
    }

    private boolean isValidSequence(List<Long> numbers) {
        boolean decrease = false;
        boolean increase = false;

        for (int i = 1; i < numbers.size(); i++) {
            long diff = Math.abs(numbers.get(i) - numbers.get(i - 1));
            if (diff < 1 || diff > 3) {
                return false;
            }

            if (numbers.get(i) > numbers.get(i - 1)) {
                increase = true;
                if (decrease) {
                    return false;
                }
            } else if (numbers.get(i) < numbers.get(i - 1)) {
                decrease = true;
                if (increase) {
                    return false;
                }
            }
        }

        return true;
    }
}
