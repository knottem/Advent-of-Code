package com.example.network;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TestClassGenerator {

    private TestClassGenerator() {}

    public static void writeTestClass(String year, String day,
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
