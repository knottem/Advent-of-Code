package com.example.year2025;

import com.example.template.Day;

import java.util.Arrays;
import java.util.List;

public class Day02 extends Day {

    public Day02(){
        super("input", "02", "2025");
    }

    @Override
    public long part1() {
        long invalidIds = 0;
        List<long[]> ranges = getRanges();
        for (long[] range : ranges) {
            for (long i =  range[0]; i <= range[1]; i++) {
                if(invalidNumber(String.valueOf(i))){
                    invalidIds += i;
                }
            }
        }
        return invalidIds;
    }

    private boolean invalidNumber(String number) {
        if(number.startsWith("0")) return true;
        int half = number.length() / 2;
        String firstHalf = number.substring(0, half);
        String secondHalf = number.substring(half);
        return firstHalf.equals(secondHalf);
    }

    @Override
    public long part2() {
        long invalidIds = 0;
        List<long[]> ranges = getRanges();
        for (long[] range : ranges) {
            for (long i =  range[0]; i <= range[1]; i++) {
                if(invalidNumberPart2V1(String.valueOf(i))){
                    invalidIds += i;
                }
            }
        }
        return invalidIds;
    }

    public static boolean invalidNumberPart2V1(String number) {
        int length = number.length();
        for (int block = 1; block <= length / 2; block++) {
            if (length % block != 0) continue;
            StringBuilder sb = new StringBuilder();
            for (long i = 0; i < length / block; i++) {
                sb.append(number, 0, block);
            }
            if (sb.toString().equals(number)) return true;
        }
        return false;
    }

    private boolean invalidNumberPart2V2(String number) {
        return (number + number).indexOf(number, 1) != number.length();
    }

    private List<long[]> getRanges(){
        return Arrays.stream(getInput().get(0).trim()
                .split(","))
                .map(s -> s.split("-"))
                .map(a -> new long[]{ Long.parseLong(a[0]), Long.parseLong(a[1]) })
                .toList();
    }


}
