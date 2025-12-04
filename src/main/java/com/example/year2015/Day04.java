package com.example.year2015;

import com.example.template.Day;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day04 extends Day {

    public Day04() {
        super("input", "04", "2015");
    }

    @Override
    public long part1() {
        return findNumberWithLeadingZeroes(5);
    }

    @Override
    public long part2() {
        return findNumberWithLeadingZeroes(6);
    }

    private long findNumberWithLeadingZeroes(int zeroes) {
        int number = 1;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            int fullZeroBytes = zeroes / 2;
            int extraBits = (zeroes % 2) * 4;
            while (true) {

                byte[] hashBytes = md.digest((getInput().get(0) + number).getBytes());

                // Check full zero bytes
                boolean allZeroes = true;
                for (int i = 0; i < fullZeroBytes; i++) {
                    if (hashBytes[i] != 0) {
                        allZeroes = false;
                        break;
                    }
                }

                // Check extra nibble if needed
                if (allZeroes && extraBits > 0) {
                    int nextByte = hashBytes[fullZeroBytes] & 0xFF;
                    if ((nextByte >> (8 - extraBits)) != 0) {
                        allZeroes = false;
                    }
                }

                if (allZeroes) {
                    break;
                }

                number++;
            }
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Faulty");
        }

        return number;
    }
}
