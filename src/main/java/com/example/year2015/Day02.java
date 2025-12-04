package com.example.year2015;

import com.example.template.Day;

public class Day02 extends Day {


    public Day02() {
        super("input.txt", "02", "2015");
    }

    @Override
    public long part1() {
        int paper = 0;
        for (String s : getInput()) {
            int length = Integer.parseInt(s.split("x")[0]);
            int width = Integer.parseInt(s.split("x")[1]);
            int height = Integer.parseInt(s.split("x")[2]);
            int area = 2 * length * width + 2 * width * height + 2 * height * length;
            int smallestSide = Math.min(Math.min(length * width, width * height), height * length);
            paper += area + smallestSide;
        }
        return paper;
    }

    @Override
    public long part2() {
        int ribbon = 0;
        for (String s : getInput()) {
            int length = Integer.parseInt(s.split("x")[0]);
            int width = Integer.parseInt(s.split("x")[1]);
            int height = Integer.parseInt(s.split("x")[2]);
            int smallestPerimeter = Math.min(Math.min(2 * length + 2 * width, 2 * width + 2 * height), 2 * height + 2 * length);
            int volume = length * width * height;
            ribbon += smallestPerimeter + volume;
        }
        return ribbon;
    }

}

