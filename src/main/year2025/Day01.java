package year2025;

import template.Day;

public class Day01 extends Day {

    public Day01(){
        super("input", "01", "2025");
    }

    @Override
    public long part1(){
        long total = 0;
        int dial = 50;
        for (String line : getInput()) {
            String dir = line.substring(0, 1);
            int number = Integer.parseInt(line.substring(1));
            dial += (dir.equals("R")) ? number : -number;
            dial %= 100;
            if(dial == 0) total++;
        }
        return total;
    }

    @Override
    public long part2(){
        long total = 0;
        int prev;
        int dial = 50;
        for (String line : getInput()) {
            prev = dial;
            String dir = line.substring(0, 1);
            int number = Integer.parseInt(line.substring(1));
            dial += (dir.equals("R")) ? number : -number;
            if (dial == 0 || prev * dial < 0) total++;
            total += Math.abs(dial / 100);
            dial %= 100;
        }
        return total;
    }
    
}
