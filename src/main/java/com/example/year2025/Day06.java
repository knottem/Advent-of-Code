package com.example.year2025;

import com.example.template.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day06 extends Day {

    List<String> operators = new ArrayList<>();
    List<String> numberLines = new ArrayList<>();

    public Day06() {
        super("06", "2025");
        for (String line : getInput()) {
            List<String> parts = Pattern.compile("\\s+").splitAsStream(line.trim()).toList();
            if ("*".equals(parts.getFirst()) || "+".equals(parts.getFirst())) {
                operators.addAll(parts);
            } else {
                numberLines.add(line);
            }
        }
    }

    @Override
    public long part1() {
        // converting to rows for part1
        List<List<Long>> numbersRow = new ArrayList<>();
        for (String line : numberLines) {
            numbersRow.add(Pattern.compile("\\s+").splitAsStream(line.trim()).map(Long::parseLong).toList());
        }
        // converting to the real numbers from column
        List<List<Long>> numbersCol = new ArrayList<>();
        for (int c = 0; c < numbersRow.getFirst().size(); c++) {
            List<Long> column = new ArrayList<>();
            for (List<Long> longs : numbersRow) {
                column.add(longs.get(c));
            }
            numbersCol.add(column);
        }
        //calculate same for both part 1 and 2 (after we actually make the Lists correct)
        return calculateSum(operators, numbersCol);
    }

    // me dum dum, cant figure out solution yet
    // could probably do it by checking char from right to left, we go from top to bottom and add all numbers until there is no more,
    // then move one step to the left, do the same and if there's a space when we move left then we're done with that List of numbers
    // when we have all List<List<Long>> then just operators.reversed and calculateSum.
    // but me dum dum, so I'll figure how to write it later
    @Override
    public long part2() {
        List<List<Long>> numbers = new ArrayList<>();
        List<Long> column = new ArrayList<>();
        for (int j = numberLines.getFirst().length() - 1; j >= 0; j--) {
            StringBuilder number = new StringBuilder();
            for (String numberLine : numberLines) {
                if (Character.isDigit(numberLine.charAt(j))) {
                    number.append(numberLine.charAt(j));
                }
            }
            try {
                long num = Long.parseLong(number.toString());
                column.add(num);
            } catch (NumberFormatException e) {
                // should only happen when it's empty String that we try to parse, then we are done with the "column"
                numbers.add(column);
                column = new ArrayList<>();
            }
        }
        //last one doesn't throw so me dum dum, took too long to figure this out
        numbers.add(column);
        return calculateSum(operators.reversed(), numbers);
    }

    private Long calculateSum(List<String> operators, List<List<Long>> numbers){
        long total = 0;
        for (int i = 0; i < operators.size(); i++) {
            long sum = operators.get(i).equals("+") ? 0 : 1;
            for (int j = 0; j < numbers.get(i).size(); j++) {
                if(operators.get(i).equals("+")){
                    sum += numbers.get(i).get(j);
                } else {
                    sum *= numbers.get(i).get(j);
                }
            }
            total += sum;
        }
        return total;
    }

}
