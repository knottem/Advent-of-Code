import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DayTemplateJava {

    private List<String> input;

    public DayTemplateJava(String text) {
        String day = ""; //day
        String year = ""; //year
        try{
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    public int part1() {
        return 0;
    }

    public int part2() {
        return 0;
    }
}
