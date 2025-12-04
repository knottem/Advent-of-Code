package com.example.year2024;

import com.example.template.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 extends Day {

    List<List<Integer>> rules = new ArrayList<>();
    List<List<Integer>> books = new ArrayList<>();

    public Day05() {
        super("input", "05", "2024");
        getRulesAndBooks();
    }


    // Safety protocols clearly indicate that new pages for the safety manuals must be printed in a very specific order.
    // The notation X|Y means that if both page number X and page number Y are to be produced as part of an update,
    // page number X must be printed at some point before page number Y.
    // For some reason, the Elves also need to know the middle page number of each update being printed.
    @Override
    public long part1() {
        long result = 0;
        for (List<Integer> book : books) {
            if(isValidBook(book)){
                result += book.get(book.size() / 2);
            }
        }
        return result;
    }


    // For each of the incorrectly-ordered updates, use the page ordering rules to put the page numbers in the right order.
    // The Elves still somehow needs the middle page number after reordering.
    @Override
    public long part2() {
        long result = 0;
        for (List<Integer> book : books) {
            if(!isValidBook(book)){
                result += reorderBook(book);
            }
        }
        return result;
    }

    private void getRulesAndBooks() {
        for (String line : getInput()){
            if(line.contains("|")){
                String[] number = line.split("\\|");
                rules.add(Arrays.asList(Integer.parseInt(number[0]), Integer.parseInt(number[1])));
            } else if(line.contains(",")){
                String[] number = line.split(",");
                List<Integer> group = new ArrayList<>();
                for (String num : number) {
                    group.add(Integer.parseInt(num));
                }
                books.add(group);
            }
        }
    }

    private boolean isValidBook(List<Integer> book) {
        for (List<Integer> rule : rules) {
            // indexOf returns -1 if not found
            int firstIndex = book.indexOf(rule.get(0));
            int secondIndex = book.indexOf(rule.get(1));

            if (firstIndex != -1 && secondIndex != -1 && firstIndex > secondIndex) {
                return false;
            }
        }
        return true;
    }

    private int reorderBook(List<Integer> book) {
        List<Integer> reordered = new ArrayList<>(book);
        boolean changed;

        do {
            changed = false;
            for (List<Integer> rule : rules) {
                // indexOf returns -1 if not found
                int firstIndex = reordered.indexOf(rule.get(0));
                int secondIndex = reordered.indexOf(rule.get(1));

                if (firstIndex != -1 && secondIndex != -1 && firstIndex > secondIndex) {
                    reordered.remove(firstIndex);
                    reordered.add(secondIndex, rule.get(0));
                    changed = true;
                }
            }
        } while (changed);

        return reordered.get(reordered.size() / 2);
    }

}
