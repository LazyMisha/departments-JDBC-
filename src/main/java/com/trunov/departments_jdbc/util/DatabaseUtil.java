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

    private static final String CREATE_DATABASE_QUERY = "CREATE DATABASE departments";

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
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createTableDepartments(){
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;
        String createDep = "create table departments(\n" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                "departments VARCHAR (30)\n" +
                ");";
        try{
            connection = getConnection();
            statement = connection.createStatement();
            ps = connection.prepareStatement(createDep);
            ps.executeUpdate();
            System.out.println("created table for departments");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createTableDevelopers(){
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;
        String createDev = "create table developers(\n" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(25) NOT NULL,\n" +
                "lastname VARCHAR(25) NOT NULL,\n" +
                "age INT NOT NULL,\n" +
                "type CHAR(1) NOT NULL,\n" +
                "language VARCHAR(20) NOT NULL,\n" +
                "department VARCHAR(20) NOT NULL" +
                ");";
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ps = connection.prepareStatement(createDev);
            ps.executeUpdate();
            System.out.println("created table for developers");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createTableManager(){
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;
        String createMan = "create table managers(\n" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                "name VARCHAR(25) NOT NULL,\n" +
                "lastname VARCHAR(25) NOT NULL,\n" +
                "age INT NOT NULL,\n" +
                "type CHAR(1) NOT NULL,\n" +
                "methodology VARCHAR(20) NOT NULL,\n" +
                "department VARCHAR(20) NOT NULL" +
                ");";
        try{
            connection = getConnection();
            statement = connection.createStatement();
            ps = connection.prepareStatement(createMan);
            ps.executeUpdate();
            System.out.println("created table for managers");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
