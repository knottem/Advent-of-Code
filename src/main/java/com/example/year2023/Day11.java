package com.example.year2023;

import com.example.template.Day;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day11 extends Day {

    public Day11() {
        super("input.txt", "11", "2023");
    }

    private int[][] getCoords(List<String> newInput) {
        int[][] coords = new int[newInput.size()][newInput.get(0).length()];
        int number = 1;
        for (int i = 0; i < newInput.size(); i++) {
            String line = newInput.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '#') {
                    coords[i][j] = number;
                    number++;
                }
            }
        }
        return coords;
    }

    private Set<Integer> findEmptyRows(int[][] coords) {
        return IntStream.range(0, coords.length)
                .filter(i -> IntStream.range(0, coords[0].length)
                        .allMatch(j -> coords[i][j] == 0))
                .boxed()
                .collect(Collectors.toSet());
    }

    private Set<Integer> findEmptyCols(int[][] coords) {
        return IntStream.range(0, coords[0].length)
                .filter(j -> Arrays.stream(coords).allMatch(row -> row[j] == 0))
                .boxed()
                .collect(Collectors.toSet());
    }

    private long getDistance(long expansionFactor) {
        int[][] coords = getCoords(getInput());
        Set<Integer> emptyRows = findEmptyRows(coords);
        Set<Integer> emptyCols = findEmptyCols(coords);
        long distance = 0;

        for (int i = 0; i < coords.length; i++) {
            for (int j = 0; j < coords[i].length; j++) {
                if (coords[i][j] != 0) {
                    distance += calculateDistanceFromPoint(coords, i, j, emptyRows, emptyCols, expansionFactor);
                }
            }
        }

        return distance / 2;
    }

    private long calculateDistanceFromPoint(int[][] coords, int x, int y, Set<Integer> emptyRows, Set<Integer> emptyCols, long expansionFactor) {
        long distance = 0;
        for (int i = 0; i < coords.length; i++) {
            for (int j = 0; j < coords[i].length; j++) {
                if (coords[i][j] != 0 && (i != x || j != y)) {
                    long rowDistance = calculateScaledDistance(x, i, emptyRows, expansionFactor);
                    long colDistance = calculateScaledDistance(y, j, emptyCols, expansionFactor);
                    distance += rowDistance + colDistance;
                }
            }
        }
        return distance;
    }

    private long calculateScaledDistance(int start, int end, Set<Integer> emptyIndices, long expansionFactor) {
        long distance = Math.abs(start - end);
        for (int index = Math.min(start, end) + 1; index < Math.max(start, end); index++) {
            if (emptyIndices.contains(index)) {
                distance += expansionFactor;
            }
        }
        return distance;
    }

    @Override
    public long part1() {
        return getDistance(1);
    }

    @Override
    public long part2() {
        return getDistance(999_999);
    }
}
