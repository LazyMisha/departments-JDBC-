package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.entity.Developer;
import com.trunov.departments_jdbc.util.DatabaseUtil;
import java.sql.*;

/**
 * Created by misha on 28.02.17.
 */
public class DevelopersDao {
    private static final String SELECT_BY_ID_QUERY = "select * from developers where id = ?";
    private static final String SELECT_BY_DEPARTMENT_NAME_QUERY = "select * from developers where department = ?";
    private static final String INSERT_INTO_DEVELOPERS_QUERY = "insert into developers\n" +
            "(name, lastname, age, type, language, department)\n" +
            "values\n" +
            "(?, ?, ?, ?, ?, ?)";
    private static final String DELETE_FROM_DEVELOPERS_QUERY = "delete from developers where id = ?";
    private static final String UPDATE_DEVELOPERS_QUERY = "update developers set name = ?, " +
            "lastname = ?, age = ?,type = ?, language = ?, department = ? where id = ?";
    private static final String SELECT_DEPARTMENT_COUNT_QUERY = "select department, count(*) from developers group by department";

    private Connection connection = null;

    public DevelopersDao() {
        DatabaseUtil.createTableDevelopers();
        connection = DatabaseUtil.getConnection();
    }

    public void getById(int id){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_BY_ID_QUERY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("Id: " + rs.getInt(1) + "\n" +
                "Name: " + rs.getString(2) + "\n" + "Last Name: " + rs.getString(3) + "\n" +
                "Age: " + rs.getInt(4) + "\n" + "Type: " + rs.getString(5) + "\n" +
                "Language: " + rs.getString(6)+ "\n" + "Department: " + rs.getString(7));
                System.out.println("<---------------------->");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void getByDepartmentName(String departmentName){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_BY_DEPARTMENT_NAME_QUERY);
            ps.setString(1, departmentName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt(1) + "\n" +
                        "Name: " + rs.getString(2) + "\n" + "Last Name: " + rs.getString(3) + "\n" +
                        "Age: " + rs.getInt(4) + "\n" + "Type: " + rs.getString(5) + "\n" +
                        "Language: " + rs.getString(6)+ "\n" + "Department: " + rs.getString(7));
                System.out.println("<---------------------->");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void save(Developer developer){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(INSERT_INTO_DEVELOPERS_QUERY);
            ps.setString(1, developer.getName());
            ps.setString(2, developer.getLastname());
            ps.setInt(3, developer.getAge());
            ps.setString(4, developer.getType());
            ps.setString(5, developer.getLanguage());
            ps.setString(6, developer.getDepartment());
            ps.executeUpdate();
            System.out.println("developer " + developer.getName() + " is successfully created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void removeById(int developerId){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(DELETE_FROM_DEVELOPERS_QUERY);
            ps.setInt(1, developerId);
            ps.executeUpdate();
            System.out.println("developer with id " + developerId + " is removed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void updateById(int id, String name, String lastname, int age, char type,
                           String language, String departments){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(UPDATE_DEVELOPERS_QUERY);
            ps.setString(1, name);
            ps.setString(2, lastname);
            ps.setInt(3, age);
            ps.setString(4, String.valueOf(type));
            ps.setString(5, language);
            ps.setString(6, departments);
            ps.setInt(7, id);
            ps.executeUpdate();
            System.out.println("developer with id " + id + " is updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void count(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_DEPARTMENT_COUNT_QUERY);
            rs.next();
            System.out.println("Department: " + rs.getString(1) + "\n" +
                    "Count of Developers: " + rs.getInt(2));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
