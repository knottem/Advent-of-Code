package year2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

//day 3 2015
public class Day03 {

    private List<String> input;

    public Day03(String text) {
        String day = "03"; //day
        String year = "2015"; //year
        try{
            String filePath = "src/resources/" + year + "/" + day + "/" + text;
            input = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }


    public int part1() {
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        int[] pos = {0, 0};
        input.forEach(s -> {
            s.chars().forEach(c -> {
                if (c == '^') {
                    pos[1]++;
                } else if (c == 'v') {
                    pos[1]--;
                } else if (c == '>') {
                    pos[0]++;
                } else if (c == '<') {
                    pos[0]--;
                }
                visited.add(pos[0] + "," + pos[1]);
            });
        });
        return visited.size();
    }

    public int part2() {
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        int[] santaPos = {0,0};
        int[] roboPos = {0,0};
        AtomicBoolean useSanta = new AtomicBoolean(true);
        input.forEach(s -> {
            s.chars().forEach(c -> {
                if (c == '^') {
                    if(useSanta.get()) santaPos[1]++;
                    else roboPos[1]++;
                } else if (c == 'v') {
                    if(useSanta.get()) santaPos[1]--;
                    else roboPos[1]--;
                } else if (c == '>') {
                    if(useSanta.get()) santaPos[0]++;
                    else roboPos[0]++;
                } else if (c == '<') {
                    if (useSanta.get()) santaPos[0]--;
                    else roboPos[0]--;
                }
                if(useSanta.get()) visited.add(santaPos[0] + "," + santaPos[1]);
                else visited.add(roboPos[0] + "," + roboPos[1]);
                useSanta.set(!useSanta.get());
            });
        });
        return visited.size();
    }

    public static void main(String[] args) {
        Day03 day03 = new Day03("input.txt");
        System.out.println(day03.part1());
        System.out.println(day03.part2());
    }
}
