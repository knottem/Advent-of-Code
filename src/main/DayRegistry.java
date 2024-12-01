import template.Day;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class DayRegistry {

    private static final Map<String, List<Day>> daysByYear = new HashMap<>();

    static {
        autoRegisterDays();
    }

    private static void autoRegisterDays() {
        String baseDir = "out/production/Advent-of-Code"; // Corrected directory name
        for (int year = 2015; year <= 2024; year++) {
            String packageName = "year" + year;
            String packagePath = baseDir + File.separator + packageName.replace('.', File.separatorChar);
            File directory = new File(packagePath);
            if (!directory.exists() || !directory.isDirectory()) {
                continue;
            }
            List<Day> days = discoverDaysForYear(packageName, baseDir);
            if (!days.isEmpty()) {
                daysByYear.put(String.valueOf(year), days);
            }
        }
    }


    private static List<Day> discoverDaysForYear(String packageName, String baseDir) {
        String packagePath = baseDir + File.separator + packageName.replace('.', File.separatorChar);

        File directory = new File(packagePath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.printf("Directory not found for package: %s%n", packageName);
            return List.of();
        }

        return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(file -> file.getName().endsWith(".class"))
                .map(file -> file.getName().replace(".class", ""))
                .map(className -> loadDayClass(packageName + "." + className))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static Day loadDayClass(String className) {
        try {
            Class<?> cls = Class.forName(className);
            if (Day.class.isAssignableFrom(cls)) {
                return (Day) cls.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            System.err.printf("Failed to load or instantiate class: %s%n", className);
        }
        return null;
    }

    public static List<Day> getDaysForYear(String year) {
        return daysByYear.getOrDefault(year, List.of());
    }
}