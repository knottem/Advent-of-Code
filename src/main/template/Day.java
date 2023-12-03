package template;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {

    private List<String> input;

    public Day(String text, String day, String year) {
        try{
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public List<String> getInputFile() {
        return input;
    }

    public int part1() {
        return 0;
    }

    public int part2() {
        return 0;
    }
}
