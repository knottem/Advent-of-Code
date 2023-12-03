package year2023;

import template.Day;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Day {

    private final List<String> input = getInputFile();

    public Day03() {
        super("input.txt", "03", "2023");
    }

    static class Position {
        int lineIndex;
        int charIndex;
        int number;
        int length;
        boolean checked = false;

        Position(int lineIndex, int charIndex, int text, int length) {
            this.lineIndex = lineIndex;
            this.charIndex = charIndex;
            this.number = text;
            this.length = length;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }

    private List<Position> getPositionsOfNumbers() {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            Matcher matcher = Pattern.compile("[0-9]+").matcher(input.get(i));
            while (matcher.find()) {
                String match = matcher.group();
                positions.add(new Position(i, matcher.start(), Integer.parseInt(match), match.length()));
            }
        }
        return positions;
    }
    @Override
    public int part1() {
        List<Position> positions = getPositionsOfNumbers();
        int sum = 0;
        for (Position position : positions) {
            if (hasSymbolNearby(position)) {
                sum += position.number;
            }
        }
        return sum;
    }

    private boolean hasSymbolNearby(Position position) {
        int lineIndex = position.lineIndex;
        int charIndex = position.charIndex;
        for (int i = lineIndex - 1; i <= lineIndex + 1; i++) {
            for (int j = charIndex - 1; j <= charIndex + position.length; j++) {
                if (i >= 0 && i < input.size() && j >= 0 && j < input.get(i).length()) {
                    char nearbyChar = input.get(i).charAt(j);
                    if (!Character.isDigit(nearbyChar) && nearbyChar != '.') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int calculateGearRatios(List<Position> positions) {
        int sum = 0;
        for (Position pos : positions) {
            if (pos.isChecked()) {
                continue;
            }
            pos.setChecked(true);
            int[] gearPositions = getGearPosition(pos.lineIndex, pos.charIndex, pos.length);
            if (gearPositions == null) {
                continue;
            }
            for (Position pos2 : positions) {
                if (checkIfNumberTouchesGear(pos2, gearPositions) && !pos2.isChecked()) {
                    sum += pos.number * pos2.number;
                    pos2.setChecked(true);
                }
            }
        }
        return sum;
    }

    private int[] getGearPosition(int lineIndex, int charIndex, int numberLength) {
        for (int i = lineIndex - 1; i <= lineIndex + 1; i++) {
            for (int j = charIndex - 1; j <= charIndex + numberLength; j++) {
                if (i >= 0 && i < input.size() && j >= 0 && j < input.get(i).length()) {
                    if (input.get(i).charAt(j) == '*') {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    private boolean checkIfNumberTouchesGear(Position position, int[] gearPosition) {
        return Math.abs(position.lineIndex - gearPosition[0]) <= 1 &&
                Math.abs(position.charIndex - gearPosition[1]) <= position.length + 1;
    }
    @Override
    public int part2() {
        return calculateGearRatios(getPositionsOfNumbers());
    }
}
