package year2024;

import template.Day;

import java.util.*;
import java.util.List;

public class Day08 extends Day {

    private record Coords(int y, int x) {}

    private char[][] map;
    private final HashMap<Character, ArrayList<Coords>> antennas = new HashMap<>();

    public Day08() {
        super("input", "08", "2024");
        initializeMapAndAntennas();
    }

    // Part 1: Determine the number of unique antinode positions
    // - Each antenna can create antinodes when perfectly in line with another antenna of the same frequency.
    // - Antinodes are only created when one antenna is twice as far as the other relative to a midpoint.
    // - Calculate and return the count of unique antinode positions within the grid bounds.
    // - Note: Used Chatgpt to understand the basis of the puzzle since I was totally lost when reading the actual puzzle
    @Override
    public long part1() {
        return computeAntinodes(false).size();
    }

    // Part 2: Incorporate resonance harmonics into antinode calculations
    // - Resonance harmonics allow for cascading antinodes along the same vector.
    // - Additionally, the antennas themselves are considered valid antinode positions.
    // - Calculate and return the count of unique antinode positions within the grid bounds with these rules applied.
    @Override
    public long part2() {
        return computeAntinodes(true).size();
    }

    private void initializeMapAndAntennas() {
        List<String> input = getInput();
        map = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            map[i] = input.get(i).toCharArray();
        }

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                char c = map[y][x];
                if (c != '.') {
                    antennas.computeIfAbsent(c, k -> new ArrayList<>())
                            .add(new Coords(y, x));
                }
            }
        }
    }

    private Set<Coords> computeAntinodes(boolean part2) {
        Set<Coords> antinodes = new HashSet<>();

        for (List<Coords> antennaList : antennas.values()) {
            for (int i = 0; i < antennaList.size(); i++) {
                Coords p1 = antennaList.get(i);
                for (int j = i + 1; j < antennaList.size(); j++) {
                    Coords p2 = antennaList.get(j);

                    int dy = p1.y - p2.y;
                    int dx = p1.x - p2.x;

                    addAntinodesAlongVector(antinodes, p1, dy, dx, part2);
                    addAntinodesAlongVector(antinodes, p2, -dy, -dx, part2);

                    if (part2) {
                        antinodes.add(p1);
                        antinodes.add(p2);
                    }
                }
            }
        }

        return antinodes;
    }

    private void addAntinodesAlongVector(Set<Coords> antinodes, Coords start, int dy, int dx, boolean resonateHarmonics) {
        int y = start.y + dy;
        int x = start.x + dx;

        while (isWithinBounds(y, x)) {
            antinodes.add(new Coords(y, x));
            if (!resonateHarmonics) break;

            y += dy;
            x += dx;
        }
    }

    private boolean isWithinBounds(int y, int x) {
        return y >= 0 && y < map.length && x >= 0 && x < map[0].length;
    }
}
