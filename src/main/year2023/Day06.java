package year2023;

import template.Day;

import java.util.Arrays;

public class Day06 extends Day {

    public Day06() {
        super("input.txt", "06", "2023");
    }

    // Part 1:
    // Your toy boat has a starting speed of zero millimeters per millisecond.
    // For each whole millisecond you spend at the beginning of the race holding down the button,
    // the boat's speed increases by one millimeter per millisecond.
    // It starts at 0 speed so if we hold it for the entire time we get 0 distance.
    // Version 2
    @Override
    public long part1() {
        long[] time = Arrays.stream(getInput().get(0).split("\\s+"))
                .skip(1)
                .mapToLong(Long::parseLong)
                .toArray();
        long[] distance = Arrays.stream(getInput().get(1).split("\\s+"))
                .skip(1)
                .mapToLong(Long::parseLong)
                .toArray();

        long totalNumbersOfWay = 1;
        for (int i = 0; i < time.length; i++) {
            totalNumbersOfWay *= calculateDistanceCount(time[i], distance[i]);
        }
        return totalNumbersOfWay;
    }

    // Part 2
    // There's really only one race - ignore the spaces between the numbers on each line.
    // Version 2
    @Override
    public long part2() {
        return calculateDistanceCount(
                Long.parseLong(getInput().get(0).substring(5).replaceAll("\\s+", "")),
                Long.parseLong(getInput().get(1).substring(9).replaceAll("\\s+", "")));
    }

    private long calculateDistanceCount(long t, long d) {
        double temp = Math.sqrt((double) t * t - 4 * d);
        int right = (int) Math.ceil((temp + t) / 2) - 1;
        int left = (int) ((t - temp) / 2) + 1;
        return right - left + 1;
    }


    /*Version 1 part1
    @Override
    public long part1() {
        int[] time = Arrays.stream(input.get(0).substring(5).trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] distance = Arrays.stream(input.get(1).substring(9).trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        long totalNumbersOfWay = 1;

        for (int i = 0; i < time.length; i++) {
            int moreDistance = 0;
            for (int j = 1; j < time[i]; j++) {
                if(j * (time[i] - j) > distance[i]) moreDistance++;
            }
            totalNumbersOfWay *= moreDistance;
        }
        return totalNumbersOfWay;
    }

    Version 1 Part 2
    @Override
    public long part2() {
        long time = Long.parseLong(input.get(0).substring(5).replaceAll("\\s+", ""));
        long distance = Long.parseLong(input.get(1).substring(9).replaceAll("\\s+", ""));
        long totalNumberOfWays = 0;
        for (int i = 1; i < time; i++) {
            if(i * (time - i) > distance) totalNumberOfWays++;
        }
        return totalNumberOfWays;
    }

     */


}