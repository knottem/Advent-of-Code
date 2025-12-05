package com.example.utils;

import com.example.template.Day;

import java.util.*;

public class DayRegistry {

    private static final Map<String, List<Day>> daysByYear = new HashMap<>();

    private static final String BASE_PACKAGE = "com.example";

    private static final int MIN_YEAR = 2015;
    private static final int MAX_YEAR = 2030;
    private static final int MAX_DAY = 25;

    public static void setMode(boolean registerLatest) {
        daysByYear.clear();
        if (registerLatest) {
            Day latest = discoverLatestDay();
            if (latest != null) {
                daysByYear.put(latest.getYear(), List.of(latest));
            }
        } else {
            autoRegisterDays();
        }
    }

    private static void autoRegisterDays() {
        for (int year = MIN_YEAR; year <= MAX_YEAR; year++) {
            List<Day> days = new ArrayList<>();
            for (int day = 1; day <= MAX_DAY; day++) {
                String className = String.format("%s.year%d.Day%02d", BASE_PACKAGE, year, day);
                Day d = loadDayClass(className);
                if (d != null) {
                    days.add(d);
                }
            }
            if (!days.isEmpty()) {
                daysByYear.put(String.valueOf(year), days);
            }
        }
    }

    private static Day discoverLatestDay() {
        for (int year = MAX_YEAR; year >= MIN_YEAR; year--) {
            for (int day = MAX_DAY; day >= 1; day--) {
                String className = String.format("%s.year%d.Day%02d", BASE_PACKAGE, year, day);
                Day d = loadDayClass(className);
                if (d != null) {
                    return d;
                }
            }
        }
        return null;
    }

    private static Day loadDayClass(String className) {
        try {
            Class<?> cls = Class.forName(className);
            if (Day.class.isAssignableFrom(cls)) {
                return (Day) cls.getDeclaredConstructor().newInstance();
            }
        } catch (ClassNotFoundException ignored) {
            // will be a lot of for example Failed to load or instantiate class: year2030.Day25 (year2030.Day25) if we set year 2030
        } catch (Exception e) {
            System.err.printf("Failed to load or instantiate class: %s (%s)%n", className, e.getMessage());
        }
        return null;
    }

    public static List<Day> getDaysForYear(String year) {
        return daysByYear.getOrDefault(year, List.of());
    }

    public static List<String> getRegisteredYears() {
        return new ArrayList<>(daysByYear.keySet());
    }
}