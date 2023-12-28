package fr.vocaltech.spring.webscraper.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class ScraperServiceImpl {
    protected Document getDocumentFromWeb(String documentUrl) {
        Document document = new Document("");

        try {
            document = Jsoup.connect(documentUrl).get();
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return document;
    }

    protected Document getDocumentFromFile(String documentFile) {
        File input = new File(documentFile);
        Document document = null;
        try {
            document = Jsoup.parse(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

}
