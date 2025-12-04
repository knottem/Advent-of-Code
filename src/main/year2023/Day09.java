package year2023;

import template.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day09 extends Day {

    public Day09() {
        super("input.txt", "09", "2023");
    }

    private long extrapolate(List<Long> numbers, boolean isPart2) {
        if (isAllZeroes(numbers) || numbers.isEmpty()) {
            return 0;
        }
        List<Long> differences = IntStream.range(0, numbers.size() - 1)
                .mapToObj(i -> numbers.get(i + 1) - numbers.get(i))
                .collect(Collectors.toList());

        return isPart2 ? numbers.get(0) - extrapolate(differences, true) : numbers.get(numbers.size() - 1) + extrapolate(differences, false);
    }

    private boolean isAllZeroes(List<Long> numbers) {
        return numbers.stream().allMatch(n -> n == 0);
    }

    private List<List<Long>> getAllLines() {
        return getInput().stream()
                .map(line -> Arrays.stream(line.trim().split("\\s+"))
                        .mapToLong(Long::parseLong)
                        .boxed()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    @Override
    public long part1() {
        return getAllLines().stream().mapToLong(line -> extrapolate(line, false)).sum();
    }

    @Override
    public long part2() {
        return getAllLines().stream().mapToLong(line -> extrapolate(line, true)).sum();
    }
}
