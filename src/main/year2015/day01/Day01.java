package year2015.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day01 {

    private String input;

    public Day01(String text) {
        String day = "01"; //day
        String year = "2015"; //year
        try{
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readString(Path.of(filePath));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    public int part1() {
        int floor = 0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '('){
                floor++;
            } else if(input.charAt(i) == ')'){
                floor--;
            }
        }
        return floor;
    }

    public int part2() {
        int floor = 0;
        int charNumber = 0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '('){
                floor++;
            } else if(input.charAt(i) == ')'){
                floor--;
            }
            if(floor == -1){
                charNumber = i;
                break;
            }
        }
        return charNumber + 1;
    }

}
