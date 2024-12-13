package utils;

import template.Day;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class DayRegistry {

    private static final Map<String, List<Day>> daysByYear = new HashMap<>();
    private static boolean useLatest = false; // Flag to switch between modes

    public static void setMode(boolean registerLatest) {
        useLatest = registerLatest;
        daysByYear.clear();
        autoRegisterDays();
    }

    private static void autoRegisterDays() {
        String baseDir = "out/production/Advent-of-Code"; // Corrected directory name

        // If useLatest is true, only register the latest day for the most recent year
        if (useLatest) {
            System.out.println("Latest mode enabled.");
            String latestYear = String.valueOf(findLatestYear(baseDir));

            String packageName = "year" + latestYear;
            Day latestDay = discoverLatestDayForYear(packageName, baseDir);
            if (latestDay != null) {
                daysByYear.put(latestYear, List.of(latestDay));
            }
        } else {
            System.out.println("Registering all days mode enabled.");
            // Otherwise, register all days for all years
            for (int year = 2015; year <= 2030; year++) {
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
    }

    private static int findLatestYear(String baseDir) {
        return Arrays.stream(Objects.requireNonNull(new File(baseDir).listFiles()))
                .map(File::getName)
                .filter(name -> name.startsWith("year"))
                .map(name -> name.replace("year", ""))
                .mapToInt(Integer::parseInt)
                .max()
                .orElseThrow(() -> new IllegalStateException("No valid year directories found."));
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

    private static Day discoverLatestDayForYear(String packageName, String baseDir) {
        String packagePath = baseDir + File.separator + packageName.replace('.', File.separatorChar);

        File directory = new File(packagePath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.printf("Directory not found for package: %s%n", packageName);
            return null;
        }

        return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(file -> file.getName().endsWith(".class"))
                .map(file -> file.getName().replace(".class", ""))
                .sorted(Comparator.reverseOrder()) // Sort descending to get the latest day
                .map(className -> loadDayClass(packageName + "." + className))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
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

    public static List<String> getRegisteredYears() {
        return new ArrayList<>(daysByYear.keySet());
    }
}