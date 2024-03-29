package year2023;

import template.Day;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 extends Day {

    public Day04() {
        super("input.txt", "04", "2023");
    }


    @Override
    public long part1() {
        int totalPoints = 0;
        for (String s : getInput()){
            String[] parts = s.split(Pattern.quote("|"));
            totalPoints += calculatePoints(
                    extractNumbers(parts[0].substring(8))
                    ,extractNumbers(parts[1]));
        }
        return totalPoints;
    }

    private List<Integer> extractNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\d+").matcher(input);
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        return numbers;
    }

    private int calculatePoints(List<Integer> winningNumbers, List<Integer> drawnNumbers) {
        int points = 0;
        for (Integer winNumber : winningNumbers) {
            for (Integer drawn : drawnNumbers) {
                if (Objects.equals(winNumber, drawn)) {
                    if (points > 0) {
                        points *= 2;
                    } else {
                        points = 1;
                    }
                }
            }
        }
        return points;
    }

    @Override
    public long part2() {
        int totalScratchBoards = 0;
        int[] extra = new int[getInput().size()];
        for (int i = 0; i < getInput().size(); i++) {
            String[] parts = getInput().get(i).split(Pattern.quote("|"));
            int scratchCards = calculateScratchboards(
                    extractNumbers(parts[0].substring(8)),
                    extractNumbers(parts[1]));
            for (int j = 0; j < extra[i] + 1; j++) {
                for (int k = 0; k < scratchCards; k++) {
                    extra[i+k+1]++;
                }
            }
            totalScratchBoards += extra[i] + 1;
        }
        return totalScratchBoards;
    }

    private int calculateScratchboards(List<Integer> winningNumbers, List<Integer> drawnNumbers) {
        int amountOfScratchboards = 0;
        for (Integer winningNumber : winningNumbers) {
            for (Integer drawn : drawnNumbers) {
                if (Objects.equals(drawn, winningNumber)) {
                    amountOfScratchboards++;
                }
            }
        }
        return amountOfScratchboards;
    }

}
