package com.example.year2024;

import com.example.template.Day;

import java.util.*;

public class Day09 extends Day {

    List<Long> numbers = new ArrayList<>();

    public Day09() {
        super("input", "09", "2024");
        String input = getInput().get(0);
        for (int i = 0; i < input.length(); i++) {
            numbers.add(Long.parseLong(String.valueOf(input.charAt(i))));
        }
    }

    // Part 1: Compact files by moving individual blocks
    // - For each free space (null), find the rightmost non-null block and swap it with the free space.
    // - Continue this process until no free spaces exist before any blocks.
    // - Remove trailing free spaces (null values) after the compaction.
    // - Calculate and return the filesystem checksum based on the compacted result.
    @Override
    public long part1() {
        List<Long> disks = getDisk();

        while (true) {
            int firstDotIndex = disks.indexOf(null);
            if (firstDotIndex == -1) {
                break; // No dots left
            }

            int lastNumberIndex = -1;
            for (int i = disks.size() - 1; i > firstDotIndex; i--) {
                if (disks.get(i) != null) {
                    lastNumberIndex = i;
                    break;
                }
            }

            if (lastNumberIndex == -1) {
                break;
            }

            // Swap the first dot and the last number
            disks.set(firstDotIndex, disks.get(lastNumberIndex));
            disks.set(lastNumberIndex, null);
        }

        // Remove trailing dots
        while (!disks.isEmpty() && disks.get(disks.size() - 1) == null) {
            disks.remove(disks.size() - 1);
        }

        return calculateChecksum(disks);
    }

    // Part 2: Compact files by moving whole files
    // - For each file ID (processed in descending order), find the leftmost span of free space large enough to fit the file.
    // - If a valid span exists, move the entire file to the free space.
    // - Ensure no unnecessary movements occur if a file is already compacted.
    // - Remove trailing free spaces (null values) after all movements.
    // - Calculate and return the filesystem checksum based on the compacted result.
    @Override
    public long part2() {
        List<Long> disks = getDisk();
        List<Long> fileIDs = disks.stream()
                .filter(Objects::nonNull)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();

        for (Long fileID : fileIDs) {
            // Find the blocks occupied by the current file
            List<Integer> fileBlocks = new ArrayList<>();
            for (int i = 0; i < disks.size(); i++) {
                if (disks.get(i) != null && disks.get(i).equals(fileID)) {
                    fileBlocks.add(i);
                }
            }
            int fileSize = fileBlocks.size();

            // Find the leftmost span of free space that can fit the file
            int freeSpaceStart = -1;
            int freeSpaceCount = 0;
            for (int i = 0; i < disks.size(); i++) {
                if (disks.get(i) == null) {
                    if (freeSpaceCount == 0) {
                        freeSpaceStart = i; // Start of free space span
                    }
                    freeSpaceCount++;
                    if (freeSpaceCount == fileSize) {
                        break; // Found a valid span
                    }
                } else {
                    freeSpaceStart = -1; // Reset the start index
                    freeSpaceCount = 0;  // Reset the count
                }
            }

            // If we found a valid span, swap the file with null
            if (freeSpaceCount == fileSize && freeSpaceStart < fileBlocks.get(0)) {
                for (int i = 0; i < fileSize; i++) {
                    disks.set(freeSpaceStart + i, fileID);
                }
                for (int index : fileBlocks) {
                    disks.set(index, null);
                }
            }
        }

        // Remove trailing dots (null values) only after all movements are done
        while (!disks.isEmpty() && disks.get(disks.size() - 1) == null) {
            disks.remove(disks.size() - 1);
        }
        return calculateChecksum(disks);
    }


    private long calculateChecksum(List<Long> blocks) {
        long checksum = 0;
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) != null) {
                checksum += i * blocks.get(i);
            }
        }
        return checksum;
    }


    private List<Long> getDisk() {
        List<Long> disks = new ArrayList<>();
        long idNumber = 0;
        boolean idNumberTime = true;

        for (Long number : numbers) {
            if (idNumberTime) {
                disks.addAll(Collections.nCopies(number.intValue(), idNumber));
                idNumber++;
            } else {
                disks.addAll(Collections.nCopies(number.intValue(), null)); // Add null disks for free space
            }
            idNumberTime = !idNumberTime;
        }
        return disks;
    }

}
