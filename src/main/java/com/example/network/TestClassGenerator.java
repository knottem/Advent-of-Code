package com.example.network;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.example.network.ExampleDownloader.*;

public class TestClassGenerator {

    private TestClassGenerator() {}

    public static void generateExampleAndTest(String year, String day) {
        try {
            Path examplePath = Paths.get(String.format("src/main/resources/%s/%s/%s", year, day, "example.txt"));
            Path testPath = Paths.get("src/test/java/com/example/", "year" + year, String.format("Day%sTest.java", day));
            Document example = null;

            boolean exampleMissing = !Files.exists(examplePath);
            boolean testMissing = !Files.exists(testPath);

            if (exampleMissing) {
                System.out.printf("Example file %s not found, downloading...%n", examplePath);
                example = downloadExample(year, day);
                Files.createDirectories(examplePath.getParent());
                Files.writeString(examplePath, parseExample(example));
            } else {
                System.out.printf("Example file %s already exists%n", examplePath);
            }

            if (testMissing) {
                String output = String.format("Example test %s not found", testPath);
                if (example == null) {
                    output = output + ", downloading...";
                    example = downloadExample(year, day);
                }
                System.out.println(output);
                String part1Expected = parseExampleAnswer(example);
                writeTestClass(year, day, part1Expected);
            } else {
                System.out.printf("Example test %s already exists%n", testPath);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private static void writeTestClass(String year, String day,
                                      String part1Expected) throws IOException {

        String packageName = "com.example.year" + year;
        String dayClassName = "Day" + day;
        String testClassName = dayClassName + "Test";

        String content = """
                package %s;
                                
                import org.junit.jupiter.api.BeforeEach;
                import org.junit.jupiter.api.Test;
                                
                import static org.junit.jupiter.api.Assertions.*;
                                
                class %s {
                                
                    @BeforeEach
                    void setUp() {
                        %s.useExample();
                    }
                                
                    @Test
                    void part1() {
                        assertEquals(%s, new %s().part1());
                    }
                                
                    @Test
                    void part2() {
                        assertEquals(0, new %s().part2());
                    }
                    
                }
                """.formatted(
                packageName,
                testClassName,
                dayClassName,
                part1Expected,
                dayClassName,
                dayClassName
        );

        Path dir = Path.of("src", "test", "java", "com", "example", "year" + year);
        Files.createDirectories(dir);
        Path file = dir.resolve(testClassName + ".java");
        Files.writeString(file, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Wrote test class to " + file);
    }

}
