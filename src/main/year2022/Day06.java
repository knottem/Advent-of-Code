package year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day06 {

    private String input;

    public Day06(String text) {
        String day = "06"; //day
        String year = "2022"; //year
        try{
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public int part1() {
        return countChars(4);
    }

    public int part2() {
        return countChars(14);
    }

    private int countChars(int amount) {
        for (int i = 0; i < input.length(); i++){
            if (input.substring(i, i+amount).chars().distinct().count() == amount){
                return i+amount;
            }
        }
        return 0;
    }
}
