package com.plorial.osvitaparser;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void createUniversityTable(){
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

    public void insertUniversityToTable(University u, int key){
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

    public TreeMap readTrainingAreasFromTable(){
        TreeMap<String, ArrayList<Integer>> sortedTrainingAreas= new TreeMap<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM UNIVERSITIES")){
            while (resultSet.next()){
                String s = resultSet.getString("TRAINING_AREAS");
                int id = resultSet.getInt("ID");
                Matcher matcher = Pattern.compile("\\((.*?)\\)").matcher(s); //O_o magic
                String res = "";
                while (matcher.find()) {
                    res = matcher.group();
                    String trainArea = res.substring(1,res.length()-1);
                    if (sortedTrainingAreas.containsKey(trainArea)) {
                       sortedTrainingAreas.get(trainArea).add(id);
                    }else {
                        ArrayList<Integer> listId = new ArrayList<>();
                        listId.add(id);
                        sortedTrainingAreas.put(trainArea, listId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedTrainingAreas;
    }

    public void createTableAndInsert(TreeMap<String, ArrayList<Integer>> map, String tableName, String keyName){
        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE " + tableName + " (" + keyName + " TEXT PRIMARY KEY NOT NULL, idOfUniversity TEXT)";
            statement.executeUpdate(sql);
            for (Map.Entry<String,ArrayList<Integer>> entry : map.entrySet()) {
                sql = "INSERT INTO " + tableName + " (" + keyName + ", idOfUniversity) VALUES ('" + entry.getKey() + "', '" + entry.getValue() + "');";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TreeMap readCitiesFromTable(){
        TreeMap<String, ArrayList<Integer>> cities= new TreeMap<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM UNIVERSITIES")){
            while (resultSet.next()){
                String s = resultSet.getString("CITY");
                int id = resultSet.getInt("ID");
                if (cities.containsKey(s)) {
                    cities.get(s).add(id);
                }else {
                    ArrayList<Integer> listId = new ArrayList<>();
                    listId.add(id);
                    cities.put(s, listId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public void closeDB(){
        try {
            connection.close();
            if(statement != null)
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DB closed");
    }
}
