package com.example.utils;

import java.util.ArrayList;
import java.util.List;

public class ParsingUtils {

    private ParsingUtils() {}

    public static List<Long> parseNumbersWithSpaces(String input) {
        List<Long> numbers = new ArrayList<>();
        for (String number : input.split("\\s+")) {
            numbers.add(Long.parseLong(number));
        }
        return numbers;
    }
}
