package year2022;

import template.Day;

public class Day06 extends Day {

    public Day06() {
        super("input.txt", "06", "2022");
    }

    @Override
    public long part1() {
        return countChars(4);
    }

    @Override
    public long part2() {
        return countChars(14);
    }

    private int countChars(int amount) {
        String input = getInput().get(0);
        for (int i = 0; i < input.length(); i++){
            if (input.substring(i, i+amount).chars().distinct().count() == amount){
                return i+amount;
            }
        }
        return 0;
    }
}
