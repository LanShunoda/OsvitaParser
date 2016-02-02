package com.plorial.osvitaparser;

import java.io.IOException;

/**
 * Created by plorial on 01.02.16.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        String URL = "http://www.osvita.com.ua/universities/";

        SQLiteJDBC db = new SQLiteJDBC();
        db.openDB();
        db.createTable();
        int counter = 1;
        for(int i = 1; i < 1000; i++) {
            Parser parser = new Parser(URL + i + "/");
            University u = parser.parse();
            if(u == null){
                System.out.println(i + " link is not valid");
                continue;
            }else {
                db.insertToTable(u, counter);
                System.out.println(i + "university added");
                counter++;
            }
        }
        System.out.println("Universities added " + counter);
        db.closeDB();
    }
}
