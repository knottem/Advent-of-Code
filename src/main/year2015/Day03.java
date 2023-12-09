package year2015;

import template.Day;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

//day 3 2015
public class Day03 extends Day {

    public Day03() {
        super("input.txt", "03", "2015");
    }

    @Override
    public long part1() {
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        int[] pos = {0, 0};
        getInput().forEach(s -> {
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

    @Override
    public long part2() {
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        int[] santaPos = {0,0};
        int[] roboPos = {0,0};
        AtomicBoolean useSanta = new AtomicBoolean(true);
        getInput().forEach(s -> {
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

}
