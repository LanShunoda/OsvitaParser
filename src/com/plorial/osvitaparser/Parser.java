package com.plorial.osvitaparser;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by plorial on 01.02.16.
 */
public class Parser {
    private String URL;

    public Parser(String URL) {
        this.URL = URL;
    }

    public University parse() throws IOException {
        Document doc;
        try {
            doc = Jsoup.connect(URL).get();
        }catch (HttpStatusException e){
            return null;
        }

        String name = doc.select("h1").get(0).text();
        String city = doc.select("span[itemprop=addressLocality]").text();
        University university = new University(name, city);
        university.setURL(URL);
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
        parseFaculties(university, doc);

        return university;
    }

    private void parseFaculties(University university, Document doc) {
        Elements faculties = doc.select("h3");
        Elements disc = doc.select("ul[type]");
        if(faculties.size() - 1 == disc.size()) {
            for (int i = 0; i < faculties.size() - 1; i++) {
                ArrayList<String> specialitiesList = new ArrayList<>();
                Elements specialities = disc.get(i).getAllElements();
                for (int j = 1; j < specialities.size(); j++) {
                    specialitiesList.add(specialities.get(j).text());
                }
                university.getFaculties().put(faculties.get(i).text(), specialitiesList);
            }
        }else{
            System.err.println("Cant parse faculties");
        }
    }

    private void parseTrainingAreas(University university, Document doc) {
        Elements firstColumn = doc.select("ul[class=grey direct1]");
        if (firstColumn.size() != 0) {
            firstColumn = firstColumn.get(0).getAllElements().select("a[rel=nofollow]");
            for (Element e : firstColumn) {
                university.getTrainingAreas().add(e.text());
            }
        }
        Elements secondColumn = doc.select("ul[class=grey direct2]");
        if (secondColumn.size() != 0) {
            secondColumn = secondColumn.get(0).getAllElements().select("a[rel=nofollow]");
            for (Element e : secondColumn) {
                university.getTrainingAreas().add(e.text());
            }
        }
        Elements column = doc.select("ul[class=grey]");
        if (column.size() != 0) {
            column = column.get(0).getAllElements().select("a[href=#]");
            for (Element e : column) {
                university.getTrainingAreas().add(e.text());
            }
        }
    }
}
