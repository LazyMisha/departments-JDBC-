package com.trunov.departments_jdbc.util;

import java.sql.*;

import static com.trunov.departments_jdbc.dao.ActionsDao.*;

/**
 * Created by misha on 21.02.17.
 */
public abstract class Database {

    public static Connection connectionToDb(){
        Connection connection = null;
        try{
            Class.forName(Driver);
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(Url, User, Password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void createDatabase() throws SQLException{
        Connection connection = null;
        PreparedStatement ps = null;
        String query = "CREATE DATABASE departments";
        try {
            connection = connectionToDb();
            ps = connection.prepareStatement(query);
            ps.executeUpdate();
            System.out.println("created util for developers");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                connection.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }

    public static void createTableDepartments() throws SQLException{
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;
        String createDep = "create table departments(\n" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                "departments VARCHAR (30)\n" +
                ");";
        try{
            connection = connectionToDb();
            statement = connection.createStatement();
            ps = connection.prepareStatement(createDep);
            ps.executeUpdate();
            System.out.println("created table for departments");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
        }
    }

    public static void createTableDevelopers() throws SQLException{
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
            connection = connectionToDb();
            statement = connection.createStatement();
            ps = connection.prepareStatement(createDev);
            ps.executeUpdate();
            System.out.println("created table for developers");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }

    public static void createTableManager() throws SQLException{
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
            connection = connectionToDb();
            statement = connection.createStatement();
            ps = connection.prepareStatement(createMan);
            ps.executeUpdate();
            System.out.println("created table for managers");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }
}
