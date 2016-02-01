package com.plorial.osvitaparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by plorial on 01.02.16.
 */
public class Parser {
    private String URL;

    public Parser(String URL) {
        this.URL = URL;
    }

    public University parse() throws IOException {
        Document doc = Jsoup.connect(URL).get();
        String name = doc.select("h1").get(0).text();
        String city = doc.select("span[itemprop=addressLocality]").text();
        University university = new University(name, city);

        Elements elements = doc.select("td");
        university.setYearOfFoundation(elements.get(3).text());
        university.setStatus(elements.get(5).text());
        university.setAccreditation(elements.get(7).text());
        university.setDocumentAfterFinish(elements.get(9).text());
        university.setFormOfEducation(elements.get(11).text());
        university.setQualificationLevels(elements.get(13).text());
        university.setAddress(elements.get(15).text());
        university.setTelephone(elements.get(17).text());
        university.setTelephoneOfSelectionCommittee(elements.get(19).text());
        university.setUniversitySite(elements.get(21).text());
        
        parseTrainingAreas(university, doc);
//        System.out.println(doc.select("ul[class=grey direct1]").size());
//        Elements e = doc.select("ul[class=grey direct1]");
//        Elements r = e.get(0).getAllElements();
//        Elements t = r.select("a[rel=nofollow]");
//        System.out.println(t.size());
//        System.out.println(t.get(0).text() + t.get(1).text());
        return university;
    }

    private void parseTrainingAreas(University university, Document doc) {
        Elements firstColumn = doc.select("ul[class=grey direct1]").get(0).getAllElements().select("a[rel=nofollow]");
        for (Element e : firstColumn) {
            university.getTrainingAreas().add(e.text());
        }
        Elements secondColumn = doc.select("ul[class=grey direct2]").get(0).getAllElements().select("a[rel=nofollow]");
        for (Element e : secondColumn) {
            university.getTrainingAreas().add(e.text());
        }
    }
}
