package com.example.year2022;


import com.example.template.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day05 extends Day {

    private final List<String> moves = new ArrayList<>();
    private final List<List<Character>> piles = new ArrayList<>();

    public Day05() {
        super("input.txt", "05", "2022");
    }

    // Method to parse the input
    private void inputParse() {
        moves.clear();
        piles.clear();
        List<String> data = new ArrayList<>();

        for (String line : getInput()) {
            if (line.startsWith("move")) {
                moves.add(line);
            } else if (line.contains("1   2")) {
                String[] parts = line.split("\\s+");
                int pileCount = Integer.parseInt(parts[parts.length - 1]);
                for (int i = 0; i < pileCount; i++) {
                    piles.add(new ArrayList<>());
                }
            } else {
                data.add(line);
            }
        }

        for (String line : data) {
            for (int index = 0; index < line.length(); index++) {
                char c = line.charAt(index);
                if (c >= 'A' && c <= 'Z') {
                    int pileIndex = (index - 1) / 4;
                    piles.get(pileIndex).add(c);
                }
            }
        }

        for (List<Character> pile : piles) {
            Collections.reverse(pile);
        }
    }

    @Override
    public long part1() {
        inputParse();

        for (String move : moves) {
            String[] parts = move.split(" ");
            int amount = Integer.parseInt(parts[1]);
            int from = Integer.parseInt(parts[3]) - 1;
            int to = Integer.parseInt(parts[5]) - 1;

            for (int i = 0; i < amount; i++) {
                piles.get(to).add(piles.get(from).remove(piles.get(from).size() - 1));
            }
        }

        StringBuilder result = new StringBuilder();
        for (List<Character> pile : piles) {
            result.append(pile.get(pile.size() - 1));
        }
        System.out.println(result);
        return 0;
    }

    @Override
    public long part2() {
        inputParse();

        for (String move : moves) {
            String[] parts = move.split(" ");
            int amount = Integer.parseInt(parts[1]);
            int from = Integer.parseInt(parts[3]) - 1;
            int to = Integer.parseInt(parts[5]) - 1;

            List<Character> pile = new ArrayList<>(piles.get(from).subList(piles.get(from).size() - amount, piles.get(from).size()));
            piles.get(from).subList(piles.get(from).size() - amount, piles.get(from).size()).clear();
            piles.get(to).addAll(pile);
        }

        StringBuilder result = new StringBuilder();
        for (List<Character> pile : piles) {
            result.append(pile.get(pile.size() - 1));
        }
        System.out.println(result);
        return 0;
    }
}