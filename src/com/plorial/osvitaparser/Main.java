package com.plorial.osvitaparser;

import java.io.IOException;
import java.util.TreeMap;

/**
 * Created by plorial on 01.02.16.
 */
public class Main {
    public static void main(String[] args) throws IOException {

//        parseURLAndAddToDataBase();
        sortTrainingAreas();
    }

    private static void sortTrainingAreas(){
        SQLiteJDBC db = new SQLiteJDBC();
        db.openDB();
        TreeMap trainAreas = db.readTrainingAreasFromTable();
        db.createTableAndInsert(trainAreas, "TrainingAreas", "TrainingArea");
        TreeMap cities = db.readCitiesFromTable();
        db.createTableAndInsert(cities, "Cities", "City");
        db.closeDB();
    }

    private static void parseURLAndAddToDataBase() throws IOException {
        String URL = "http://www.osvita.com.ua/universities/";

        SQLiteJDBC db = new SQLiteJDBC();
        db.openDB();
        db.createUniversityTable();
        int counter = 1;
        for(int i = 1; i < 1000; i++) {
            Parser parser = new Parser(URL + i + "/");
            University u = parser.parse();
            if(u == null){
                System.out.println(i + " link is not valid");
                continue;
            }else {
                db.insertUniversityToTable(u, counter);
                System.out.println(i + " university added");
                counter++;
            }
        }
        System.out.println("Universities added " + (counter - 1));
        db.closeDB();
    }
}
