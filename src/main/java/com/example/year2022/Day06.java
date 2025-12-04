package com.example.year2022;

import com.example.template.Day;

import java.util.HashMap;
import java.util.Map;

public class Day06 extends Day {

    public Day06() {
        super("input.txt", "06", "2022");
    }

    @Override
    public long part1() {
        return countCharsBitmask(4);
    }

    @Override
    public long part2() {
        return countCharsBitmask(14);
    }

    // Brute force solution
    private int countChars(int amount) {
        String input = getInput().get(0);
        for (int i = 0; i < input.length(); i++){
            if (input.substring(i, i+amount).chars().distinct().count() == amount){
                return i+amount;
            }
        }
        return 0;
    }

    // Faster solution using a sliding window
    private int countCharsSlidingWindow(int amount) {
        String input = getInput().get(0);
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int left = 0;

        for (int right = 0; right < input.length(); right++) {
            char currentChar = input.charAt(right);

            // If the current character is already in the window, move the left pointer
            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }

            // Update the position of the current character in the map
            charIndexMap.put(currentChar, right);

            // Check if the window size matches the required amount
            if (right - left + 1 == amount) {
                return right + 1;
            }
        }
        return 0; // No valid substring found
    }

    // Even faster solution using a bitmask
    private int countCharsBitmask(int amount) {
        String input = getInput().get(0);
        int bitmask = 0;
        int left = 0;

        for (int right = 0; right < input.length(); right++) {
            int charBit = 1 << (input.charAt(right) - 'a');

            // Check if the character is already in the bitmask
            while ((bitmask & charBit) != 0) {
                // Remove the leftmost character from the bitmask
                bitmask &= ~(1 << (input.charAt(left) - 'a'));
                left++;
            }

            // Add the current character to the bitmask
            bitmask |= charBit;

            // Check if the window size matches the required amount
            if (right - left + 1 == amount) {
                return right + 1;
            }
        }
        return 0; // No valid substring found
    }
}
