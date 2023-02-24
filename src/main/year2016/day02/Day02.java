package year2016.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Day02 {

    private List<String> input;

    public Day02(String text) {
        String day = "02"; //day
        String year = "2016"; //year
        try{
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    static int[][] key = {
            { 1, 2, 3},
            { 4, 5, 6},
            { 7, 8, 9}};
    // 10 = A, 11 = B, 12 = C, 13 = D
    static int[][] key2 = {
            { 0, 0, 1, 0, 0},
            { 0, 2, 3, 4, 0},
            { 5, 6, 7, 8, 9},
            { 0, 10, 11, 12, 0},
            { 0, 0, 13, 0, 0}};

    public String part1() {
        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(1);
        coordinates.add(1);
        AtomicReference<String> result = new AtomicReference<>("");
        input.forEach(s -> {
                    for (int i = 0; i < s.length(); i++) {
                        switch (s.charAt(i)) {
                            case 'U' -> {
                                if (coordinates.get(1) > 0) {
                                    coordinates.set(1, coordinates.get(1) - 1);
                                }
                            }
                            case 'D' -> {
                                if (coordinates.get(1) < 2) {
                                    coordinates.set(1, coordinates.get(1) + 1);
                                }
                            }
                            case 'L' -> {
                                if (coordinates.get(0) > 0) {
                                    coordinates.set(0, coordinates.get(0) - 1);
                                }
                            }
                            case 'R' -> {
                                if (coordinates.get(0) < 2) {
                                    coordinates.set(0, coordinates.get(0) + 1);
                                }
                            }
                        }
                    }
                    result.updateAndGet(v -> v + key[coordinates.get(1)][coordinates.get(0)]);
                });
        return result.get();
    }

    public String part2(){
        int x = 0;
        int y = 2;
        StringBuilder result = new StringBuilder();
        for (String s : input) {
            for (char c : s.toCharArray()) {
                switch (c) {
                    case 'U' -> y = (y > 0 && key2[y - 1][x] != 0) ? y - 1 : y;
                    case 'D' -> y = (y < 4 && key2[y + 1][x] != 0) ? y + 1 : y;
                    case 'L' -> x = (x > 0 && key2[y][x - 1] != 0) ? x - 1 : x;
                    case 'R' -> x = (x < 4 && key2[y][x + 1] != 0) ? x + 1 : x;
                }
            }
            int code = key2[y][x];
            if (code == 10) result.append('A');
            else if (code == 11) result.append('B');
            else if (code == 12) result.append('C');
            else if (code == 13) result.append('D');
            else result.append(code);
        }
        return result.toString();

    }


    public static void main(String[] args) {
        Day02 day02 = new Day02("input.txt");
        System.out.println(day02.part1());
        System.out.println(day02.part2());
    }
}
