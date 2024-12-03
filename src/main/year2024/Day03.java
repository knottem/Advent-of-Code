package year2024;

import template.Day;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Day {


    public Day03() {
        super("input", "03", "2024");
    }


    // Find valid mul(X,Y) instructions (1-3 digit numbers, e.g., mul(44,46)).
    // Ignore invalid characters and malformed instructions, then sum the multiplication results.
    @Override
    public long part1() {
        long result = 0;
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        for (int i = 0; i < getInput().size(); i++) {
            Matcher matcher = pattern.matcher(getInput().get(i));
            while (matcher.find()) {
                result += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
            }
        }
        return result;
    }

    // Add support for do() (enables) and don't() (disables) instructions to control mul(X,Y) processing.
    // At start, mul is enabled; only enabled instructions contribute to the sum.
    @Override
    public long part2() {
        long result = 0;
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Pattern doDont = Pattern.compile("(don't\\(\\)|do\\(\\))");

        boolean enabled = true;
        for (int i = 0; i < getInput().size(); i++) {
            String line = getInput().get(i);
            Matcher matcher = pattern.matcher(line);
            Matcher doMatcher = doDont.matcher(line);
            int stop = 0;
            while (stop < line.length()) {
                int nextMul = matcher.find(stop) ? matcher.start() : Integer.MAX_VALUE;
                int nextInstruction = doMatcher.find(stop) ? doMatcher.start() : Integer.MAX_VALUE;
                if (nextMul < nextInstruction) {
                    if (enabled) result += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
                    stop = matcher.end();
                    continue;
                }
                if (nextInstruction < Integer.MAX_VALUE) {
                    enabled = doMatcher.group().equals("do()");
                    stop = doMatcher.end();
                    continue;
                }
                break;
            }
        }
        return result;
    }
}
