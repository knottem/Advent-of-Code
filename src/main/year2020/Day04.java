package year2020;

import template.Day;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 extends Day {


    private final List<Map<String, String>> input;

    public Day04() {
        super("passports.txt", "04", "2020");
        this.input = getInputAsMaps();
    }

    private List<Map<String, String>> getInputAsMaps() {
        String fileContents = String.join("\n", getInput());
        String[] passportStrings = fileContents.split("\n\n");
        return Arrays.stream(passportStrings)
                .map(passportString -> Arrays.stream(passportString.split("[ \n]"))
                        .map(entry -> entry.split(":"))
                        .collect(Collectors.toMap(
                                keyValue -> keyValue[0],
                                keyValue -> keyValue[1]
                        ))
                )
                .collect(Collectors.toList());
    }

    private boolean isValid(Map<String, String> passport) {
        return passport.keySet().containsAll(Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
    }

    private boolean isValidField(String key, String value) {
        return switch (key) {
            case "byr" -> {
                int byr = Integer.parseInt(value);
                yield byr >= 1920 && byr <= 2002;
            }
            case "iyr" -> {
                int iyr = Integer.parseInt(value);
                yield iyr >= 2010 && iyr <= 2020;
            }
            case "eyr" -> {
                int eyr = Integer.parseInt(value);
                yield eyr >= 2020 && eyr <= 2030;
            }
            case "hgt" -> {
                if (value.endsWith("cm")) {
                    int heightCm = Integer.parseInt(value.substring(0, value.length() - 2));
                    yield heightCm >= 150 && heightCm <= 193;
                } else if (value.endsWith("in")) {
                    int heightIn = Integer.parseInt(value.substring(0, value.length() - 2));
                    yield heightIn >= 59 && heightIn <= 76;
                }
                yield false;
            }
            case "hcl" -> Pattern.matches("#[0-9a-f]{6}", value);
            case "ecl" -> Pattern.matches("amb|blu|brn|gry|grn|hzl|oth", value);
            case "pid" -> Pattern.matches("[0-9]{9}", value);
            default -> true; // Ignore unknown fields
        };
    }

    @Override
    public long part1() {
        return (int) input.stream()
                .filter(this::isValid)
                .count();
    }

    @Override
    public long part2() {
        return (int) input.stream()
                .filter(this::isValid)
                .filter(passport -> passport.entrySet().stream()
                        .allMatch(entry -> isValidField(entry.getKey(), entry.getValue())))
                .count();
    }

}
