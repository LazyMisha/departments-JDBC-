package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.entity.Developer;
import com.trunov.departments_jdbc.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    private static final String SEARCH_ALL_EMPLOYEE_QUERY = "select * from developers where age = ? and department = ?";

    private Connection connection = null;

    public DevelopersDao() {
        DatabaseUtil.createTableDevelopers();
        connection = DatabaseUtil.getConnection();
    }

    public Developer getById(int id){
        PreparedStatement ps = null;
        Developer developer = new Developer();
        try{
            ps = connection.prepareStatement(SELECT_BY_ID_QUERY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                developer.setId(rs.getInt(1));
                developer.setName(rs.getString(2));
                developer.setLastName(rs.getString(3));
                developer.setAge(rs.getInt(4));
                developer.setType(rs.getString(5));
                developer.setLanguage(rs.getString(6));
                developer.setDepartment(rs.getString(7));
            }
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
        return developer;
    }

    public List<Developer> getAllByDepartmentName(String departmentName){
        PreparedStatement ps = null;
        List<Developer> developers = new ArrayList<>();
        try {
            ps = connection.prepareStatement(SELECT_BY_DEPARTMENT_NAME_QUERY);
            ps.setString(1, departmentName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Developer developer = new Developer();
                developer.setId(rs.getInt(1));
                developer.setName(rs.getString(2));
                developer.setLastName(rs.getString(3));
                developer.setAge(rs.getInt(4));
                developer.setType(rs.getString(5));
                developer.setLanguage(rs.getString(6));
                developer.setDepartment(rs.getString(7));
                developers.add(developer);
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
        return developers;
    }

    public void save(Developer developer){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(INSERT_INTO_DEVELOPERS_QUERY);
            ps.setString(1, developer.getName());
            ps.setString(2, developer.getLastName());
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

    public List<String> count(){
        Statement statement = null;
        List<String> department = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_DEPARTMENT_COUNT_QUERY);
            rs.next();
            department.add(rs.getString(1));
            department.add(rs.getString(2));
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
        return department;
    }

    public List<Developer> searchEmployee(int age, String departmentName){
        PreparedStatement ps = null;
        List<Developer> developers = new ArrayList<>();
        try {
            ps = connection.prepareStatement(SEARCH_ALL_EMPLOYEE_QUERY);
            ps.setInt(1, age);
            ps.setString(2, departmentName);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Developer developer = new Developer();
                developer.setId(resultSet.getInt(1));
                developer.setName(resultSet.getString(2));
                developer.setType(resultSet.getString(5));
                developer.setDepartment(resultSet.getString(7));
                developers.add(developer);
            }
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
        return developers;
    }
}
