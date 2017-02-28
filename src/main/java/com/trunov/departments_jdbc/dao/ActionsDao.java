package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.util.Database;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by misha on 21.02.17.
 */
public abstract class ActionsDao {
    public static int idEmp;
    public static String str;
    public static String Url = "jdbc:mysql://localhost/departments?autoReconnect=true&useSSL=false";
    public static String Driver = "com.mysql.jdbc.Driver";
    public static String User = "root";
    public static String Password = "mysql";
    public static String arr[];
    public static ArrayList<String> list = new ArrayList<>();

    public static void openEmployeesById(int id) throws SQLException{
        DevelopersDao.openById(id);
        ManagersDao.openById(id);
    }

    public static void openEmployeesByDepartmentName(String departmentName) throws SQLException{
        DevelopersDao.openByDepartmentName(departmentName);
        ManagersDao.openByDepartmentName(departmentName);
    }

    public static void openAll() throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String printAll = "select department, name, type, age from managers " +
                "union all " +
                "select department, name, type, age from developers";
        try {
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(printAll);
            while(resultSet.next()){
                System.out.println("Department Name: " + resultSet.getString(1) +
                        ", Employee Name: " + resultSet.getString(2) +
                ", Employee Type: " + resultSet.getString(3) +
                ", Employee Age: " + resultSet.getInt(4));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
    }

    public static void searchEmployee(int age, String department) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String searchEmployee = "select * from managers where age = " + age + " and department = '" + department + "'" +
                " union all " +
                "select * from developers where age = " + age + " and department = '" + department + "'";
        try {
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchEmployee);
            while (resultSet.next()){
                System.out.println("id: " + resultSet.getInt(1) +
                        ", Name: " + resultSet.getString(2) +
                        ", Type: " + resultSet.getString(5) +
                        ", Department: " + resultSet.getString(7));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
    }

}
