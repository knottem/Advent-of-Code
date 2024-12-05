package year2024;

import template.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 extends Day {

    public Day05() {
        super("input", "05", "2024");
    }


    // Safety protocols clearly indicate that new pages for the safety manuals must be printed in a very specific order.
    // The notation X|Y means that if both page number X and page number Y are to be produced as part of an update,
    // page number X must be printed at some point before page number Y.
    // For some reason, the Elves also need to know the middle page number of each update being printed.
    @Override
    public long part1() {
        List<List<List<Integer>>> rulesAndBooks = getRulesAndBooks();
        List<List<Integer>> rules = rulesAndBooks.get(0);
        List<List<Integer>> books = rulesAndBooks.get(1);
        long result = 0;
        for (List<Integer> book : books) {
            if(isValidBook(book, rules)){
                result += book.get(book.size() / 2);
            }
        }
        return result;
    }


    // For each of the incorrectly-ordered updates, use the page ordering rules to put the page numbers in the right order.
    // The Elves still somehow needs the middle page number after reordering.
    @Override
    public long part2() {
        List<List<List<Integer>>> rulesAndBooks = getRulesAndBooks();
        List<List<Integer>> rules = rulesAndBooks.get(0);
        List<List<Integer>> books = rulesAndBooks.get(1);
        long result = 0;
        for (List<Integer> book : books) {
            if(!isValidBook(book, rules)){
                result += reorderBook(book, rules).get(book.size() / 2);
            }
        }
        return result;
    }

    private List<List<List<Integer>>> getRulesAndBooks() {
        List<List<List<Integer>>> rulesAndBooks = new ArrayList<>();
        List<List<Integer>> rules = new ArrayList<>();
        List<List<Integer>> books = new ArrayList<>();
        boolean pairs = true;
        for (String line : getInput()){
            if(line.isEmpty()){
                pairs = false;
                continue;
            }
            if(pairs){
                String[] number = line.split("\\|");
                rules.add(Arrays.asList(Integer.parseInt(number[0]), Integer.parseInt(number[1])));
            } else {
                String[] number = line.split(",");
                List<Integer> group = new ArrayList<>();
                for (String num : number) {
                    group.add(Integer.parseInt(num));
                }
                books.add(group);
            }
        }
        rulesAndBooks.add(rules);
        rulesAndBooks.add(books);
        return rulesAndBooks;
    }

    private boolean isValidBook(List<Integer> book, List<List<Integer>> rules) {
        for (List<Integer> rule : rules) {
            int firstIndex = book.indexOf(rule.get(0));
            int secondIndex = book.indexOf(rule.get(1));

            if (firstIndex != -1 && secondIndex != -1 && firstIndex > secondIndex) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> reorderBook(List<Integer> book, List<List<Integer>> rules) {
        List<Integer> reordered = new ArrayList<>(book);
        boolean changed;
        // we know a rule will change so it's safe to make a do loop
        do {
            changed = false;
            for (List<Integer> rule : rules) {
                int firstIndex = reordered.indexOf(rule.get(0));
                int secondIndex = reordered.indexOf(rule.get(1));

                if (firstIndex != -1 && secondIndex != -1 && firstIndex > secondIndex) {
                    reordered.remove(firstIndex);
                    reordered.add(secondIndex, rule.get(0));
                    changed = true;
                }
            }
        } while (changed);

        return reordered;
    }

}
