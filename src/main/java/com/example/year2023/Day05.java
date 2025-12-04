package com.example.year2023;

import com.example.template.Day;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day05 extends Day {

    public Day05() {
        super("input.txt", "05", "2023");
    }

    @Override
    public long part1() {
        List<Long> seeds = getSeeds();
        Map<String, List<Long>> mapData = getMapData();
        for (String mapType : Arrays.asList("seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water",
                "water-to-light", "light-to-temperature", "temperature-to-humidity", "humidity-to-location")) {
            seeds = updateList(seeds, mapData.get(mapType));
        }
        return Collections.min(seeds);
    }

    // Slow solution, but it works, takes 15min+
    @Override
    public long part2() {
        List<Long> currentSeeds = getSeeds();
        Map<String, List<Long>> mapData = getMapData();
        long lowestLocation = Long.MAX_VALUE;
        int chunkSize = 10000000;

        ExecutorService executorService = new ThreadPoolExecutor(
                10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        for (int i = 0; i < currentSeeds.size(); i += 2) {
            long seedStart = currentSeeds.get(i);
            long seedRange = currentSeeds.get(i + 1);
            int runs = (int) Math.ceil((double) seedRange / chunkSize);
            System.out.println("-----------------------------------");
            System.out.println("Current seedStart: " + seedStart);
            System.out.println("Total seeds this round: " + seedRange);
            System.out.println("Nr: " + (i / 2 + 1) + " out of: " + currentSeeds.size() / 2);
            System.out.println("Current lowest: " + lowestLocation);
            System.out.println("Amount of runs this round: " + runs);
            System.out.println("-----------------------------------");

            for (long chunkStart = seedStart; chunkStart < seedStart + seedRange; chunkStart += chunkSize) {
                long chunkEnd = Math.min(chunkStart + chunkSize, seedStart + seedRange);
                System.out.println("RunNr: " + runs + " Current chunk: " + chunkStart + " -> " + (chunkStart + chunkSize) +
                        " chunk left: " + (seedStart + seedRange - chunkEnd) + " Current lowest: " + lowestLocation);
                List<Long> seeds = LongStream.range(chunkStart, chunkEnd)
                        .boxed()
                        .collect(Collectors.toList());

                for (String mapType : Arrays.asList("seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water",
                        "water-to-light", "light-to-temperature", "temperature-to-humidity", "humidity-to-location")) {
                    seeds = updateList(seeds, mapData.get(mapType));
                }

                if (Collections.min(seeds) < lowestLocation) {
                    long lowestSeed = Collections.min(seeds);
                    System.out.println("New lowest location: " + lowestSeed);
                    lowestLocation = lowestSeed;
                }
                runs--;
            }
        }
        return lowestLocation;
    }

    private List<Long> getSeeds(){
        String[] seedValues = getInput().get(0).substring(7).trim().split(" ");
        return new ArrayList<>(Arrays.stream(seedValues).map(Long::parseLong).toList());
    }

    private Map<String, List<Long>> getMapData(){
        Map<String, List<Long>> mapData = new HashMap<>();
        mapData.put("seed-to-soil", new ArrayList<>());
        mapData.put("soil-to-fertilizer", new ArrayList<>());
        mapData.put("fertilizer-to-water", new ArrayList<>());
        mapData.put("water-to-light", new ArrayList<>());
        mapData.put("light-to-temperature", new ArrayList<>());
        mapData.put("temperature-to-humidity", new ArrayList<>());
        mapData.put("humidity-to-location", new ArrayList<>());
        String currentMapType = "";
        for (int i = 1; i < getInput().size(); i++) {
            if(getInput().get(i).endsWith("map:")){
                currentMapType = getInput().get(i).substring(0, getInput().get(i).length() - 5).trim();
            } else if (!currentMapType.isEmpty()) {
                if (getInput().get(i).matches("\\d+\\s+\\d+.*")) {
                    List<Long> values = Arrays.stream(getInput().get(i).trim().split("\\s+"))
                            .map(Long::parseLong)
                            .toList();
                    mapData.get(currentMapType).addAll(values);
                }
            }
        }
        return mapData;
    }

    private List<Long> updateList(List<Long> sourceList, List<Long> mappingList) {
        List<Long> updatedList = new ArrayList<>(sourceList);
        for (int i = 0; i < updatedList.size(); i++) {
            long sourceNumber = updatedList.get(i);
            for (int j = 0; j < mappingList.size(); j += 3) {
                long destinationRangeStart = mappingList.get(j);
                long sourceRangeStart = mappingList.get(j + 1);
                long rangeLength = mappingList.get(j + 2);
                if (sourceNumber >= sourceRangeStart && sourceNumber < sourceRangeStart + rangeLength) {
                    long destinationNumber = destinationRangeStart + (sourceNumber - sourceRangeStart);
                    updatedList.set(i, destinationNumber);
                    break;
                }
            }
        }
        return updatedList;
    }

}

