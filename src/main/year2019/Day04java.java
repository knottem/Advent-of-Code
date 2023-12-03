package year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day04java {

    private int input;
    private int input2;

    public Day04java(String text) {
        String day = "04";
        String year = "2019";
        try {
            Path path = Path.of("src/resources/" + year + "/" + day + "/" + text);
            input = Integer.parseInt(Files.readString(path).substring(0, 6));
            input2 = Integer.parseInt(Files.readString(path).substring(Files.readString(path).length() - 6));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean validPassword(int password){
        String passwordStr = String.valueOf(password);
        boolean valid = false;
        for (int i = 1; i < passwordStr.length(); i++) {
            if(passwordStr.charAt(i - 1) > passwordStr.charAt(i)){
                return false;
            }
            if(passwordStr.charAt(i - 1) == passwordStr.charAt(i)){
                valid = true;
            }
        }
        return valid;
    }

    private boolean validPassword2(int password) {
        boolean valid = false;
        int count = 1;
        String passwordStr = String.valueOf(password);
        for (int i = 1; i < passwordStr.length(); i++) {
            if (passwordStr.charAt(i - 1) > passwordStr.charAt(i)) {
                return false;
            }
            if (passwordStr.charAt(i - 1) == passwordStr.charAt(i)) {
                count++;
            } else {
                if (count == 2) {
                    valid = true;
                }
                count = 1;
            }
        }
        if (count == 2) {
            valid = true;
        }
        return valid;
    }

    public int part1() {
        int count = 0;
        for (int i = input; i < input2; i++) {
            if(validPassword(i)){
                count++;
            }
        }
        return count;
    }

    public int part2() {
        int count = 0;
        for (int i = input; i < input2; i++) {
            if(validPassword2(i)){
                count++;
            }
        }
        return count;
    }
}
