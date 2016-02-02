package com.plorial.osvitaparser;

import java.io.IOException;
import java.sql.Connection;

/**
 * Created by plorial on 01.02.16.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        Parser parser = new Parser("http://www.osvita.com.ua/universities/150/");
        University u = parser.parse();
        System.out.println(u.toString());
        SQLiteJDBC db = new SQLiteJDBC();
        db.openDB();
        db.createTable();
        db.insertToTable(u,1);
        parser = new Parser("http://www.osvita.com.ua/universities/120/");
        University u2 = parser.parse();
        db.insertToTable(u2,2);
        db.closeDB();
    }
}
