package com.plorial.osvitaparser;

import java.io.IOException;

/**
 * Created by plorial on 01.02.16.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        Parser parser = new Parser("http://www.osvita.com.ua/universities/150/");
        University u = parser.parse();
        System.out.println(u.toString());

    }
}
