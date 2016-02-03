package com.plorial.osvitaparser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by plorial on 02.02.16.
 */
public class SQLiteJDBC {
    private Connection connection;
    private Statement statement;

    public void openDB(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:universities.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");
    }

    public void createTable(){
        try {
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder("CREATE TABLE if not exists UNIVERSITIES ")
                    .append("(ID INT PRIMARY KEY     NOT NULL,")
                    .append("NAME TEXT NOT NULL,")
                    .append("CITY TEXT NOT NULL,")
                    .append("YEAR TEXT,")
                    .append("STATUS TEXT,")
                    .append("ACCREDITATION TEXT,")
                    .append("DOCUMENT TEXT,")
                    .append("FORM_OF_EDUCATION TEXT,")
                    .append("QUALIFICATION TEXT,")
                    .append("ADDRESS TEXT,")
                    .append("TELEPHONE TEXT,")
                    .append("TELEPHONE_OF_SELECTION_COMMITTEE TEXT,")
                    .append("SITE TEXT,")
                    .append("URL TEXT,")
                    .append("TRAINING_AREAS TEXT,")
                    .append("FACULTIES TEXT);");
            statement.executeUpdate(sql.toString());
//            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Table created successfully");
    }

    public void insertToTable(University u, int key){
        StringBuilder sql = new StringBuilder("INSERT INTO UNIVERSITIES (ID, NAME, CITY, YEAR, STATUS, ACCREDITATION, ")
                .append("DOCUMENT, FORM_OF_EDUCATION, ")
                .append("QUALIFICATION, ADDRESS, TELEPHONE, TELEPHONE_OF_SELECTION_COMMITTEE , SITE, URL, TRAINING_AREAS, FACULTIES) ")
                .append("VALUES (")
                .append(key)
                .append(", '")
                .append(u.name)
                .append("', '")
                .append(u.city)
                .append("', '")
                .append(u.getYearOfFoundation())
                .append("', '")
                .append(u.getStatus())
                .append("', '")
                .append(u.getAccreditation())
                .append("', '")
                .append(u.getDocumentAfterFinish())
                .append("', '")
                .append(u.getFormOfEducation())
                .append("', '")
                .append(u.getQualificationLevels())
                .append("', '")
                .append(u.getAddress())
                .append("', '")
                .append(u.getTelephone())
                .append("', '")
                .append(u.getTelephoneOfSelectionCommittee())
                .append("', '")
                .append(u.getUniversitySite())
                .append("', '")
                .append(u.getURL())
                .append("', '")
                .append(u.getTrainingAreas())
                .append("', '")
                .append(u.getFaculties())
                .append("');");
        try {
            statement.executeUpdate(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DB closed");
    }
}
