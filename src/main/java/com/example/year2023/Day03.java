package com.example.year2023;

import com.example.template.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Day {

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
        for (int i = 0; i < getInput().size(); i++) {
            Matcher matcher = Pattern.compile("[0-9]+").matcher(getInput().get(i));
            while (matcher.find()) {
                String match = matcher.group();
                positions.add(new Position(i, matcher.start(), Integer.parseInt(match), match.length()));
            }
        }
        return positions;
    }
    @Override
    public long part1() {
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
                if (i >= 0 && i < getInput().size() && j >= 0 && j < getInput().get(i).length()) {
                    char nearbyChar = getInput().get(i).charAt(j);
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

            int[] gearPosition = getGearPosition(pos.lineIndex, pos.charIndex, pos.length);
            if (gearPosition != null) {
                for (Position otherPosition : positions) {
                    if (!otherPosition.isChecked() && isNearby(gearPosition, otherPosition)) {
                        sum += (pos.number * otherPosition.number);
                        otherPosition.setChecked(true);
                    }
                }
            }
        }

        return sum;
    }

    private int[] getGearPosition(int lineIndex, int charIndex, int numberLength) {
        for (int i = lineIndex - 1; i <= lineIndex + 1; i++) {
            for (int j = charIndex - 1; j <= charIndex + numberLength; j++) {
                if (i >= 0 && i < getInput().size() && j >= 0 && j < getInput().get(i).length()) {
                    char nearbyChar = getInput().get(i).charAt(j);
                    if (nearbyChar == '*') {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }


    private boolean isNearby(int[] gearPosition, Position otherPosition) {
        int gearLineIndex = gearPosition[0];
        int gearCharIndex = gearPosition[1];
        int lineIndex = otherPosition.lineIndex;
        int charIndex = otherPosition.charIndex;

        return Math.abs(gearLineIndex - lineIndex) <= 1 &&
                Math.abs(gearCharIndex - charIndex) <= otherPosition.length + 1;
    }
    @Override
    public long part2() {
        return calculateGearRatios(getPositionsOfNumbers());
    }
}
