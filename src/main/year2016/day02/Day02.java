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

    static int[][] key = { { 1, 2, 3}, { 4, 5, 6}, {7, 8, 9}};
    static int[][] key2 = { { 0, 0, 1, 0, 0}, { 0, 2, 3, 4, 0}, {5, 6, 7, 8, 9}, { 0, 10, 11, 12, 0}, { 0, 0, 13, 0, 0}};

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

    public String part2() {
        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(0);
        coordinates.add(2);
        AtomicReference<String> result = new AtomicReference<>("");
        input.forEach(s -> {
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case 'U' -> {
                        if (coordinates.get(1) > 0 && key2[coordinates.get(1) - 1][coordinates.get(0)] != 0) {
                            coordinates.set(1, coordinates.get(1) - 1);
                        }
                    }
                    case 'D' -> {
                        if (coordinates.get(1) < 4 && key2[coordinates.get(1) + 1][coordinates.get(0)] != 0) {
                            coordinates.set(1, coordinates.get(1) + 1);
                        }
                    }
                    case 'L' -> {
                        if (coordinates.get(0) > 0 && key2[coordinates.get(1)][coordinates.get(0) - 1] != 0) {
                            coordinates.set(0, coordinates.get(0) - 1);
                        }
                    }
                    case 'R' -> {
                        if (coordinates.get(0) < 4 && key2[coordinates.get(1)][coordinates.get(0) + 1] != 0) {
                            coordinates.set(0, coordinates.get(0) + 1);
                        }
                    }
                }
            }
            if(key2[coordinates.get(1)][coordinates.get(0)] == 10){
                result.updateAndGet(v -> v + "A");
            } else if(key2[coordinates.get(1)][coordinates.get(0)] == 11){
                result.updateAndGet(v -> v + "B");
            } else if(key2[coordinates.get(1)][coordinates.get(0)] == 12){
                result.updateAndGet(v -> v + "C");
            } else if(key2[coordinates.get(1)][coordinates.get(0)] == 13){
                result.updateAndGet(v -> v + "D");
            } else{
                result.updateAndGet(v -> v + key2[coordinates.get(1)][coordinates.get(0)]);
            }
        });
        return result.get();
    }

    public static void main(String[] args) {
        Day02 day02 = new Day02("input.txt");
        System.out.println(day02.part1());
        System.out.println(day02.part2());
    }
}
