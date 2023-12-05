package year2023;

import template.Day;

import java.util.*;

public class Day05 extends Day {

    List<String> input = getInputFile();

    public Day05() {
        super("example.txt", "05", "2023");
    }


    @Override
    public long part1() {
        long start = System.nanoTime();
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
        for (String line : input) {
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
                "water-to-light", "light-to-temperature", "temperature-to-humidity", "humidity-to-location")){
            seeds = updateList(seeds, mapData.get(mapType));
        }
        long end = System.nanoTime();
        System.out.println("Part 1 Time: " + ((double) (end - start) / 1_000_000_000));
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

    @Override
    public long part2() {
        long start = System.nanoTime();
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
        for (String line : input) {
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
        seeds = updateSeeds(seeds, mapData);
        long end = System.nanoTime();
        System.out.println("Part 2 Time: " + ((double) (end - start) / 1_000_000_000));
        return seeds.get(0);
    }

    private List<Long> updateSeeds(List<Long> seedRanges, Map<String, List<Long>> mapData) {
        long smallest = Long.MAX_VALUE;
        List<Long> updatedSeedRanges = new ArrayList<>();

        for (String mapType : Arrays.asList(
                "seed-to-soil",
                "soil-to-fertilizer",
                "fertilizer-to-water",
                "water-to-light",
                "light-to-temperature",
                "temperature-to-humidity",
                "humidity-to-location")) {

            long localSmallest = Long.MAX_VALUE;

            for (int i = 0; i < seedRanges.size(); i += 2) {
                long input = seedRanges.get(i);
                long range = seedRanges.get(i + 1);

                for (long j = 0; j < range; j++) {
                    long result = input + j;
                    List<Long> map = mapData.get(mapType);
                        for (int k = 0; k < map.size(); k += 3) {
                            long destinationRangeStart = map.get(k);
                            long sourceRangeStart = map.get(k + 1);
                            long rangeLength = map.get(k + 2);
                            if (result >= sourceRangeStart && result < sourceRangeStart + rangeLength) {
                                result = destinationRangeStart + (result - sourceRangeStart);
                            }
                        }

                    if (result < localSmallest) {
                        localSmallest = result;
                    }
                }
            }

            if (localSmallest < smallest) {
                smallest = localSmallest;
            }
        }

        updatedSeedRanges.add(smallest);
        return updatedSeedRanges;
    }

}
