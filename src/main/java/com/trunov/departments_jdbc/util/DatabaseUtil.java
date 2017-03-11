package com.trunov.departments_jdbc.util;

import java.sql.*;

/**
 * Created by misha on 21.02.17.
 */
public abstract class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost/departments?autoReconnect=true&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";

    private static final String CREATE_DATABASE_QUERY = "CREATE DATABASE if not exists departments";
    private static final String CREATE_TABLE_DEPARTMENTS_QUERY = "create table if not exists departments(\n" +
            "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
            "departments VARCHAR (30)\n" +
            ");";
    private static final String CREATE_TABLE_DEVELOPERS_QUERY = "create table if not exists developers(\n" +
            "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
            "name VARCHAR(25) NOT NULL,\n" +
            "lastname VARCHAR(25) NOT NULL,\n" +
            "age INT NOT NULL,\n" +
            "type CHAR(1) NOT NULL,\n" +
            "language VARCHAR(20) NOT NULL,\n" +
            "department VARCHAR(20) NOT NULL" +
            ");";
    private static final String CREATE_TABLE_MANAGERS_QUERY = "create table if not exists managers(\n" +
            "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
            "name VARCHAR(25) NOT NULL,\n" +
            "lastname VARCHAR(25) NOT NULL,\n" +
            "age INT NOT NULL,\n" +
            "type CHAR(1) NOT NULL,\n" +
            "methodology VARCHAR(20) NOT NULL,\n" +
            "department VARCHAR(20) NOT NULL" +
            ");";

    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection == null) {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void createDatabase(){
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute(CREATE_DATABASE_QUERY);
            System.out.println("created util for developers");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void createTableDepartments(){
        createTable(CREATE_TABLE_DEPARTMENTS_QUERY);
    }

    public static void createTableDevelopers(){
        createTable(CREATE_TABLE_DEVELOPERS_QUERY);
    }

    public static void createTableManager(){
        createTable(CREATE_TABLE_MANAGERS_QUERY);
    }

    private static void createTable(String query) {
        PreparedStatement ps = null;
        try{
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
