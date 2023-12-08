package year2023;

import template.Day;

import java.util.*;
import java.util.stream.IntStream;

public class Day07 extends Day {

    public Day07() {
        super("input.txt", "07", "2023");
    }

    private record CardsData(String card, int value) { }

    public int[] cardsStrength(String card, boolean part2) {
        List<Integer> cardsList = new ArrayList<>();
        for (int i = 0; i < card.length(); i++) {
            char c = card.charAt(i);
            if (c == 'A') cardsList.add(14);
            else if (c == 'K') cardsList.add(13);
            else if (c == 'Q') cardsList.add(12);
            else if (c == 'J') cardsList.add(part2 ? -1 : 11);
            else if (c == 'T') cardsList.add(10);
            else if (Character.isDigit(c)) cardsList.add(Character.getNumericValue(c));
        }

        if (part2) {
            return new int[] { findHighestValue(cardsList, 0)[0], cardsList.get(0), cardsList.get(1), cardsList.get(2), cardsList.get(3), cardsList.get(4)};
        }
        return checkCardValue(cardsList);
    }

    public int[] findHighestValue(List<Integer> cardsList, int index) {
        if (index == cardsList.size()) {
            return checkCardValue(new ArrayList<>(cardsList));
        }
        int[] highest = new int[] { -1 };
        if (cardsList.get(index) == -1) {
            for (int j = 2; j <= 14; j++) {
                cardsList.set(index, j);
                int[] result = findHighestValue(cardsList, index + 1);
                if (result[0] > highest[0]) {
                    highest = result.clone();
                    if (highest[0] == 10) {
                        cardsList.set(index, -1);
                        return highest;
                    }
                }
                cardsList.set(index, -1);
            }
        } else {
            return findHighestValue(cardsList, index + 1);
        }
        return highest;
    }

    private int[] checkCardValue(List<Integer> cardsList) {
        int[] result = new int[]{0, cardsList.get(0), cardsList.get(1), cardsList.get(2), cardsList.get(3), cardsList.get(4)};
        cardsList.sort(Comparator.comparingInt((Integer o) ->
                Collections.frequency(cardsList, o)).thenComparingInt(o -> o));
        if (IntStream.rangeClosed(0, 4).mapToObj(cardsList::get).distinct().count() == 1) result[0] = 10;
        else if (IntStream.rangeClosed(1, 4).mapToObj(cardsList::get).distinct().count() == 1) result[0] = 9;
        else if (Objects.equals(cardsList.get(0), cardsList.get(1))
                && IntStream.rangeClosed(2, 4).mapToObj(cardsList::get).distinct().count() == 1) result[0] = 8;
        else if (IntStream.rangeClosed(2, 4).mapToObj(cardsList::get).distinct().count() == 1) result[0] = 7;
        else if (Objects.equals(cardsList.get(1), cardsList.get(2))
                && Objects.equals(cardsList.get(3), cardsList.get(4))) result[0] = 6;
        else if (Objects.equals(cardsList.get(3), cardsList.get(4))) result[0] = 5;
        else result[0] = 4;
        return result;
    }

    private List<CardsData> createList() {
        List<CardsData> cardsList = new ArrayList<>();
        for (int i = 0; i < getInput().size(); i++) {
            String[] cards = getInput().get(i).split(" ");
            cardsList.add(new CardsData(cards[0], Integer.parseInt(cards[1])));
        }
        return cardsList;
    }

    private long calculateAnswer(List<CardsData> cardsList) {
        int ans = 0;
        for (int i = 0; i < cardsList.size() ; i++) {
            ans += (i + 1) * cardsList.get(i).value;
        }
        return ans;
    }

    @Override
    public long part1() {
        List<CardsData> cardsList = createList();
        cardsList.sort((o1, o2) -> {
            int[] str1 = cardsStrength(o1.card, false);
            int[] str2 = cardsStrength(o2.card, false);
            for (int i = 0; i < str1.length; i++) {
                if (str1[i] != str2[i]) {
                    return Integer.compare(str1[i], str2[i]);
                }
            }
            return 0;
        });

        return calculateAnswer(cardsList);
    }

    @Override
    public long part2() {
        List<CardsData> cardsList = createList();
        cardsList.sort((o1, o2) -> {
            int[] str1 = cardsStrength(o1.card, true);
            int[] str2 = cardsStrength(o2.card, true);
            for (int i = 0; i < str1.length; i++) {
                if (str1[i] != str2[i]) {
                    return Integer.compare(str1[i], str2[i]);
                }
            }
            return 0;
        });

        return calculateAnswer(cardsList);
    }
}
