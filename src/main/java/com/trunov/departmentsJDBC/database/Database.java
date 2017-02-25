package com.trunov.departmentsJDBC.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static com.trunov.departmentsJDBC.dao.ActionsDao.*;

/**
 * Created by misha on 21.02.17.
 */
public class Database {

    public static void createDatabase (){
        try {
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            String query = "CREATE DATABASE departments";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();

            System.out.println("created database for developers");

            connection.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("database developers already exist");
        }
    }

    public static void createTableDepartments(){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String createDep = "create table departments(\n" +
                    "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                    "departments VARCHAR (30)\n" +
                    ");";
            PreparedStatement ps = connection.prepareStatement(createDep);
            ps.executeUpdate();
            System.out.println("created table for departments");

            ps.close();
            connection.close();
            statement.close();
        }catch (Exception e){
            System.out.println("table departments already exist");
        }
    }

    public static void createTableDevelopers() {
        try {
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String createDev = "create table developers(\n" +
                    "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                    "name VARCHAR(25) NOT NULL,\n" +
                    "lastname VARCHAR(25) NOT NULL,\n" +
                    "age INT NOT NULL,\n" +
                    "type CHAR(1) NOT NULL,\n" +
                    "language VARCHAR(20) NOT NULL,\n" +
                    "department VARCHAR(20) NOT NULL" +
                    ");";
            PreparedStatement ps = connection.prepareStatement(createDev);
            ps.executeUpdate();

            System.out.println("created table for developers");

            connection.close();
            statement.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("table developers already exist");
        }
    }

    public static void createTableManager(){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String createMan = "create table managers(\n" +
                    "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                    "name VARCHAR(25) NOT NULL,\n" +
                    "lastname VARCHAR(25) NOT NULL,\n" +
                    "age INT NOT NULL,\n" +
                    "type CHAR(1) NOT NULL,\n" +
                    "methodology VARCHAR(20) NOT NULL,\n" +
                    "department VARCHAR(20) NOT NULL" +
                    ");";
            PreparedStatement ps = connection.prepareStatement(createMan);
            ps.executeUpdate();

            System.out.println("created table for managers");

            connection.close();
            statement.close();
            ps.close();
        }catch(Exception e){
            System.out.println("table managers already exist");
        }
    }
}
