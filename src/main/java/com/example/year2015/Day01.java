package com.example.year2015;

import com.example.template.Day;

public class Day01 extends Day {


    public Day01() {
        super("input.txt", "01", "2015");
    }

    @Override
    public long part1() {
        String input = getInput().get(0);
        int floor = 0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '('){
                floor++;
            } else if(input.charAt(i) == ')'){
                floor--;
            }
        }
        return floor;
    }

    @Override
    public long part2() {
        String input = getInput().get(0);
        int floor = 0;
        int charNumber = 0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '('){
                floor++;
            } else if(input.charAt(i) == ')'){
                floor--;
            }
            if(floor == -1){
                charNumber = i;
                break;
            }
        }
        return charNumber + 1;
    }

}
