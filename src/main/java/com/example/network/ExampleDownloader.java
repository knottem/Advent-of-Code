package com.example.network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
            throw new IllegalStateException("Cant find codeblock for parsing Example: " + doc.title());
        }
        return codeBlock.text();
    }

    public static String parseExampleAnswer(Document doc) {
        for (Element em : doc.select("article.day-desc em:has(code)")) {
            assert em.parent() != null;
            String sentence = em.parent().text().toLowerCase();
            if (sentence.contains("example")) {
                Element codeBlock = em.selectFirst("code");
                if (codeBlock != null) {
                    return codeBlock.text();
                }
            }
        }
        System.err.println("Cant find codeblock for parsing exampleAnswer: " + doc.title());
        return "0";
    }

}
