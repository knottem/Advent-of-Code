package year2023;

import template.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        int steps = 0;
        while(true){
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
                if (current.operation.equals("ZZZ")) {
                    return steps;
                }
            }
        }
    }


    @Override
    public long part2() {
       return 0;
    }

}

