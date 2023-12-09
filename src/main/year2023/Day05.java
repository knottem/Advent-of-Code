package year2023;

import template.Day;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day05 extends Day {

    public Day05() {
        super("input.txt", "05", "2023");
    }


    @Override
    public long part1() {
        List<Long> seeds = new ArrayList<>();
        Map<String, List<Long>> mapData = new HashMap<>();
        mapData.put("seed-to-soil", new ArrayList<>());
        mapData.put("soil-to-fertilizer", new ArrayList<>());
        mapData.put("fertilizer-to-water", new ArrayList<>());
        mapData.put("water-to-light", new ArrayList<>());
        mapData.put("light-to-temperature", new ArrayList<>());
        mapData.put("temperature-to-humidity", new ArrayList<>());
        mapData.put("humidity-to-location", new ArrayList<>());
        String currentMapType = "";
        for (String line : getInput()) {
            if (line.startsWith("seeds:")) {
                String[] seedValues = line.substring(7).trim().split(" ");
                seeds.addAll(Arrays.stream(seedValues).map(Long::parseLong).toList());
            } else if (line.endsWith("map:")) {
                currentMapType = line.substring(0, line.length() - 5).trim();
            } else if (!currentMapType.isEmpty()) {
                if (line.matches("\\d+\\s+\\d+.*")) {
                    List<Long> values = Arrays.stream(line.trim().split("\\s+"))
                            .map(Long::parseLong)
                            .toList();
                    mapData.get(currentMapType).addAll(values);
                }
            }
        }
        for (String mapType : Arrays.asList("seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water",
                "water-to-light", "light-to-temperature", "temperature-to-humidity", "humidity-to-location")) {
            seeds = updateList(seeds, mapData.get(mapType));
        }
        return Collections.min(seeds);
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


    // Slow solution, but it works.
    @Override
    public long part2() {
        Map<String, List<Long>> mapData = new HashMap<>();
        mapData.put("seed-to-soil", new ArrayList<>());
        mapData.put("soil-to-fertilizer", new ArrayList<>());
        mapData.put("fertilizer-to-water", new ArrayList<>());
        mapData.put("water-to-light", new ArrayList<>());
        mapData.put("light-to-temperature", new ArrayList<>());
        mapData.put("temperature-to-humidity", new ArrayList<>());
        mapData.put("humidity-to-location", new ArrayList<>());
        String currentMapType = "";

        long lowestLocation = Long.MAX_VALUE;
        List<Long> currentSeeds = new ArrayList<>();

        for (String line : getInput()) {
            if (line.startsWith("seeds:")) {
                String[] seedValues = line.substring(7).trim().split(" ");
                for (String seedValue : seedValues) {
                    currentSeeds.add(Long.parseLong(seedValue));
                }
            } else if (line.endsWith("map:")) {
                currentMapType = line.substring(0, line.length() - 5).trim();
            } else if (!currentMapType.isEmpty()) {
                if (line.matches("\\d+\\s+\\d+.*")) {
                    List<Long> values = Arrays.stream(line.trim().split("\\s+"))
                            .map(Long::parseLong)
                            .toList();

                    mapData.get(currentMapType).addAll(values);

                }
            }

        }

        int chunkSize = 10000000;
        for (int i = 0; i < currentSeeds.size(); i += 2) {
            long seedStart = currentSeeds.get(i);
            long seedRange = currentSeeds.get(i + 1);

            System.out.println("Current seed: " + seedStart + " with range: " + seedRange);

            for (long chunkStart = seedStart; chunkStart < seedStart + seedRange; chunkStart += chunkSize) {
                long chunkEnd = Math.min(chunkStart + chunkSize, seedStart + seedRange);
                System.out.println("Current chunk: " + chunkStart + " -> " + (chunkStart + chunkSize) +
                        " chunk left: " + (seedStart + seedRange - chunkEnd) + " of seed nr: " + (i+1));
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
            }
        }
        return lowestLocation;
    }

}

