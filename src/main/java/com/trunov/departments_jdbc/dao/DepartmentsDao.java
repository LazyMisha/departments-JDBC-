package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.util.Database;
import java.sql.*;

/**
 * Created by misha on 28.02.17.
 */
public abstract class DepartmentsDao {

    public static void open() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet departments = null;
        String sqlDepartments = "SELECT * FROM departments";
        try{
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            departments = statement.executeQuery(sqlDepartments);
            while(departments.next()){
                System.out.println("id: " + departments.getInt(1) +
                        ", Name: " + departments.getString(2) + ".");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(statement != null){
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }
            if(departments != null) {
                departments.close();
            }
        }
    }

    public static void create(String departmentName) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;
        String createDep = "insert into departments (departments)\n" +
                "values ('" + departmentName + "')";
        try{
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            ps = connection.prepareStatement(createDep);
            ps.executeUpdate();
            System.out.println("department is successfully created!");
        }catch (SQLException e){
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

    public static void removeByName(String departmentName) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;
        String deleteDep = "delete from departments where departments = '" + departmentName + "'";
        try {
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            ps = connection.prepareStatement(deleteDep);
            ps.executeUpdate();
            System.out.println("department " + departmentName + " is removed!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if (connection != null) {
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
