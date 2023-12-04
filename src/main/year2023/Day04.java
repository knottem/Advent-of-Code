package year2023;

import template.Day;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day04 extends Day {

    List<String> input = getInputFile();

    public Day04() {
        super("input.txt", "04", "2023");
    }


    @Override
    public int part1() {
        int totalPoints = 0;
        for (String s : input){
            String[] parts = s.split(Pattern.quote("|"));
            totalPoints += calculatePoints(
                    extractNumbers(parts[0].substring(8))
                    ,extractNumbers(parts[1]));
        }
        return totalPoints;
    }

    private List<Integer> extractNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

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
    public int part2() {
        int totalScratchBoards = 0;
        int[] extra = new int[input.size() + 10];
        for (int i = 0; i < input.size(); i++) {
            for (int j = -1; j < extra[i]; j++) {
                String[] parts = input.get(i).split(Pattern.quote("|"));
                List<Integer> winningNumbers = extractNumbers(parts[0].substring(8));
                List<Integer> drawnNumbers = extractNumbers(parts[1]);
                int scratchCards = calculateScratchboards(winningNumbers, drawnNumbers);
                for (int k = 0; k < scratchCards; k++) {
                    extra[i+k+1]++;
                }
                totalScratchBoards++;
            }
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
