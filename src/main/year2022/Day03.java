package year2022;

import template.Day;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;

public class Day03 extends Day {

    public Day03() {
        super("input.txt", "03", "2022");
    }

    private int sumOfPriorities(List<String> list) {
        return list.stream()
                .map(s -> s.chars().boxed().collect(toSet()))
                .reduce((set1, set2) -> {
                    set1.retainAll(set2);
                    return set1;
                })
                .orElseThrow()
                .stream()
                .mapToInt(c -> Character.isLowerCase(c) ? c - 'a' + 1 : c - 'A' + 27)
                .sum();
    }

    @Override
    public long part1() {
        return getInput().stream()
                .map(s -> s.substring(0, s.length() / 2) + " " + s.substring(s.length() / 2))
                .map(s -> List.of(s.split(" ")))
                .mapToLong(this::sumOfPriorities)
                .sum();
    }

    @Override
    public long part2() {
        return IntStream.range(0, getInput().size() / 3)
                .mapToLong(i -> sumOfPriorities(getInput().subList(i * 3, Math.min(i * 3 + 3, getInput().size()))))
                .sum();
    }

}
