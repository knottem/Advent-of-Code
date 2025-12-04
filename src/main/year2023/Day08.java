package year2023;

import template.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day08 extends Day {

    public Day08() {
        super("input.txt", "08", "2023");
    }

    private record Instruction(String operation, String left, String right) { }


    @Override
    public long part1() {
        List<Character> input = getInput().get(0).chars().mapToObj(c -> (char) c).toList();
        List<Instruction> instructions = new ArrayList<>();
        for (int i = 2; i < getInput().size(); i++) {
            String[] line = getInput().get(i).split(" ");
            instructions.add(new Instruction(line[0], line[2].replace("(", "").replace(",", ""), line[3].replace(")", "")));
        }
        Instruction current = instructions.stream().filter(instruction -> instruction.operation.equals("AAA")).findFirst().get();
        return stepsFinder(instructions, current, input, "ZZZ");
    }

    private int stepsFinder(List<Instruction> instructions, Instruction current, List<Character> input, String end) {
        int steps = 0;
        while (true) {
            for (Character character : input) {
                steps++;
                if (character == 'L') {
                    for (Instruction instruction : instructions) {
                        if (instruction.operation.equals(current.left)) {
                            current = instruction;
                            break;
                        }
                    }
                } else {
                    for (Instruction instruction : instructions) {
                        if (instruction.operation.equals(current.right)) {
                            current = instruction;
                            break;
                        }
                    }
                }
                if (current.operation.endsWith(end)) {
                    return steps;
                }
            }
        }
    }

    private long lcm(long x, long y) {
        long max = Math.max(x, y);
        long min = Math.min(x, y);
        long lcm = max;
        while (lcm % min != 0) {
            lcm += max;
        }
        return lcm;
    }

    @Override
    public long part2() {
        List<Character> input = getInput().get(0).chars().mapToObj(c -> (char) c).toList();
        List<Instruction> instructions = new ArrayList<>();
        for (int i = 2; i < getInput().size(); i++) {
            String[] line = getInput().get(i).split(" ");
            instructions.add(new Instruction(line[0], line[2].replace("(", "").replace(",", ""), line[3].replace(")", "")));
        }

        List<Instruction> start = instructions.stream().filter(instruction -> instruction.operation.endsWith("A")).toList();
        List<Integer> steps = new ArrayList<>();
        for (Instruction instruction : start) {
            steps.add(stepsFinder(instructions, instruction, input, "Z"));
        }
        long lcm = 1;
        for (Integer step : steps) {
            lcm = lcm(lcm, step);
        }
        return lcm;
    }

}

