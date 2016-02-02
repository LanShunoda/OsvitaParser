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
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");
    }

    public void createTable(){
        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE if not exists UNIVERSITIES " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    "NAME TEXT NOT NULL," +
                    "CITY TEXT NOT NULL)";
            statement.executeUpdate(sql);
//            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Table created successfully");
    }

    public void insertToTable(University u, int key){
        String sql = "INSERT INTO UNIVERSITIES (ID, NAME, CITY) " +
                "VALUES (" + key + ", '"+ u.name + "', '"+ u.city + "');";
        try {
            statement.executeUpdate(sql);
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
