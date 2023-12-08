package year2023;

import template.Day;

import java.util.*;
import java.util.stream.IntStream;

public class Day07 extends Day {

    public Day07() {
        super("example2.txt", "07", "2023");
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
        List<Integer> copy = new ArrayList<>(cardsList);
        int[] result = new int[]{0, copy.get(0), copy.get(1), copy.get(2), copy.get(3), copy.get(4)};
        // Sort the ranks by frequency, then by value.
        cardsList.sort(Comparator.comparingInt((Integer o) ->
                Collections.frequency(cardsList, o)).thenComparingInt(o -> o));

        // Check for the various hands.
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
            System.out.println(cardsList.get(i).card + " " + cardsList.get(i).value);
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
