package year2022;

import template.Day;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04 extends Day {

    public Day04() {
        super("input.txt", "04", "2022");
    }

    private static class Pair {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean contains(Pair other) {
            return this.start <= other.start && other.end <= this.end;
        }

        public boolean overlaps(Pair other) {
            return this.start <= other.end && other.start <= this.end;
        }
    }

    private List<Pair> parseRanges(String line) {
        String[] rangeStrings = line.split(",");
        return Stream.of(rangeStrings)
                .map(rangeStr -> {
                    String[] bounds = rangeStr.split("-");
                    int start = Integer.parseInt(bounds[0]);
                    int end = Integer.parseInt(bounds[1]);
                    return new Pair(start, end);
                })
                .collect(Collectors.toList());
    }

    @Override
    public long part1() {
        List<String> inputList = getInput();
        List<List<Pair>> rangesList = inputList.stream()
                .map(this::parseRanges)
                .toList();

        return rangesList.stream()
                .filter(ranges -> {
                    Pair a = ranges.get(0);
                    Pair b = ranges.get(1);
                    return a.contains(b) || b.contains(a);
                })
                .count();
    }

    @Override
    public long part2(){
        List<String> inputList = getInput();
        List<List<Pair>> rangesList = inputList.stream()
                .map(this::parseRanges)
                .toList();

        return rangesList.stream()
                .filter(ranges -> {
                    Pair a = ranges.get(0);
                    Pair b = ranges.get(1);
                    return a.overlaps(b);
                })
                .count();
    }


}
