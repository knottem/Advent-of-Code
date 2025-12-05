package com.example.network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class ExampleDownloader {

    private ExampleDownloader() {}

    public static Document downloadExample(String year, String day) {
        String url = "https://adventofcode.com/" + year + "/day/" + Integer.parseInt(day); //just to remove 0 infront

        try {
            HttpResponse<String> response = null;
            try (HttpClient client = HttpClient.newHttpClient()) {

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("User-Agent", "knottem")
                        .GET()
                        .build();

                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            assert response != null;
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed: HTTP " + response.statusCode() + " from " + url);
            }
            return Jsoup.parse(response.body());
        } catch (InterruptedException e) {
            throw new RuntimeException("Error downloading for " + year + " day " + day, e);
        }
    }

    public static String parseExample(Document doc) {
        Element codeBlock = doc.selectFirst("article.day-desc pre code");
        if (codeBlock == null) {
            throw new IllegalStateException("Cant find codeblock: " + doc.title());
        }
        return codeBlock.text();
    }

    public static String parseExampleAnswer(Document doc) {
        for (Element em : doc.select("article.day-desc em:has(code)")) {
            assert em.parent() != null;
            String sentence = em.parent().text().toLowerCase();
            if (sentence.contains("example")) {
                Element code = em.selectFirst("code");
                if (code != null) {
                    return code.text();
                }
            }
        }
        throw new IllegalStateException("Cant find codeblock: " + doc.title());
    }

    public static void writeTestClass(String year, String day,
                                       String part1Expected) throws IOException {

        String packageName = "com.example.year" + year;
        String dayClassName = "Day" + day;
        String testClassName = dayClassName + "Test";

        String content = """
                package %s;
                                
                import com.example.template.Day;
                import org.junit.jupiter.api.BeforeEach;
                import org.junit.jupiter.api.Test;
                                
                import static org.junit.jupiter.api.Assertions.*;
                                
                class %s {
                                
                    @BeforeEach
                    void setUp() {
                        Day.useExample();
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
                part1Expected,
                dayClassName,
                dayClassName
        );

        Path dir = Path.of("src", "test", "java",
                "com", "example", "year" + year);
        Files.createDirectories(dir);
        Path file = dir.resolve(testClassName + ".java");
        Files.writeString(file, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Wrote test class to " + file);
    }
}
