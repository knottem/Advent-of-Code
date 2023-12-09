package year2019;

import template.Day;
import utils.Pair;

import java.util.*;

public class Day03 extends Day {

    public Day03()  {
        super("input.txt", "03", "2019");
    }
    private List<Pair<Integer, Integer>> getWirePath(List<String> wire) {
        List<Pair<Integer, Integer>> path = new ArrayList<>();
        Pair<Integer, Integer> current = new Pair<>(0, 0);

        for (String instruction : wire) {
            char direction = instruction.charAt(0);
            int distance = Integer.parseInt(instruction.substring(1));

            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case 'U' -> current = new Pair<>(current.getFirst(), current.getSecond() + 1);
                    case 'D' -> current = new Pair<>(current.getFirst(), current.getSecond() - 1);
                    case 'L' -> current = new Pair<>(current.getFirst() - 1, current.getSecond());
                    case 'R' -> current = new Pair<>(current.getFirst() + 1, current.getSecond());
                }
                path.add(current);
            }
        }

        return path;
    }

    @Override
    public long part1() {
        Set<Pair<Integer, Integer>> path1 = new HashSet<>(getWirePath(List.of(getInput().get(0).split(","))));
        Set<Pair<Integer, Integer>> path2 = new HashSet<>(getWirePath(List.of(getInput().get(1).split(","))));

        path1.retainAll(path2);

        return path1.stream()
                .mapToInt(pair -> Math.abs(pair.getFirst()) + Math.abs(pair.getSecond()))
                .min()
                .orElse(0);
    }

    @Override
    public long part2() {
        List<Pair<Integer, Integer>> path1 = getWirePath(List.of(getInput().get(0).split(",")));
        List<Pair<Integer, Integer>> path2 = getWirePath(List.of(getInput().get(1).split(",")));

        Set<Pair<Integer, Integer>> set1 = new HashSet<>(path1);
        Set<Pair<Integer, Integer>> set2 = new HashSet<>(path2);

        set1.retainAll(set2);

        return set1.stream()
                .mapToInt(pair -> path1.indexOf(pair) + path2.indexOf(pair) + 2)
                .min()
                .orElse(0);
    }
}
