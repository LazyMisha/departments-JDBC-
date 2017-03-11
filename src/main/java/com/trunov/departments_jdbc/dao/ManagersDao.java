package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.entity.Manager;
import com.trunov.departments_jdbc.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 28.02.17.
 */
public class ManagersDao {
    private static final String SELECT_BY_ID_QUERY = "select * from managers where id = ?";
    private static final String SELECT_BY_DEPARTMENT_NAME_QUERY = "select * from managers where department = ?";
    private static final String INSERT_INTO_MANAGERS_QUERY = "insert into managers\n" +
            "(name, lastname, age, type, methodology, department)\n" +
            "values\n" +
            "(?, ?, ?, ?, ?, ?)";
    private static final String DELETE_FROM_MANAGERS_QUERY = "delete from managers where id = ?";
    private static final String UPDATE_MANAGERS_QUERY = "update managers set name = ?, " +
            "lastname = ?, age = ?,type = ?, methodology = ?, department = ? where id = ?";
    private static final String SELECT_MANAGERS_COUNT_QUERY = "select department, count(*) from managers group by department";
    private static final String SEARCH_ALL_EMPLOYEE_QUERY = "select * from managers where age = ? and department = ?";

    Connection connection = null;

    public ManagersDao() {
        DatabaseUtil.createTableManager();
        connection = DatabaseUtil.getConnection();
    }

    public Manager getById(int id){
        PreparedStatement ps = null;
        Manager manager = new Manager();
        try{
            ps = connection.prepareStatement(SELECT_BY_ID_QUERY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                manager.setId(rs.getInt(1));
                manager.setName(rs.getString(2));
                manager.setLastName(rs.getString(3));
                manager.setAge(rs.getInt(4));
                manager.setType(rs.getString(5));
                manager.setMethodology(rs.getString(6));
                manager.setDepartment(rs.getString(7));
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
        return manager;
    }

    public List<Manager> getAllByDepartmentName(String departmentName){
        PreparedStatement ps = null;
        List<Manager> managers = new ArrayList<>();
        try {
            ps = connection.prepareStatement(SELECT_BY_DEPARTMENT_NAME_QUERY);
            ps.setString(1, departmentName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Manager manager = new Manager();
                manager.setId(rs.getInt(1));
                manager.setName(rs.getString(2));
                manager.setLastName(rs.getString(3));
                manager.setAge(rs.getInt(4));
                manager.setType(rs.getString(5));
                manager.setMethodology(rs.getString(6));
                manager.setDepartment(rs.getString(7));
                managers.add(manager);
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
        return managers;
    }

    public void save(Manager manager){
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(INSERT_INTO_MANAGERS_QUERY);
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getLastName());
            ps.setInt(3, manager.getAge());
            ps.setString(4, manager.getType());
            ps.setString(5, manager.getMethodology());
            ps.setString(6,manager.getDepartment());
            ps.executeUpdate();
            System.out.println("manager " + manager.getName() + " is successfully created!");
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

    public void removeById(int managerId){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(DELETE_FROM_MANAGERS_QUERY);
            ps.setInt(1, managerId);
            ps.executeUpdate();
            System.out.println("manager with id " + managerId + " is removed!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
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
                                         String methodology, String departments){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(UPDATE_MANAGERS_QUERY);
            ps.setString(1, name);
            ps.setString(2, lastname);
            ps.setInt(3, age);
            ps.setString(4, String.valueOf(type));
            ps.setString(5, methodology);
            ps.setString(6, departments);
            ps.setInt(7, id);
            ps.executeUpdate();
            System.out.println("manager with id " + id + " is updated!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
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
            ResultSet rs = statement.executeQuery(SELECT_MANAGERS_COUNT_QUERY);
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

    public List<Manager> searchEmployee(int age, String departmentName){
        PreparedStatement ps = null;
        List<Manager> managers = new ArrayList<>();
        try {
            ps = connection.prepareStatement(SEARCH_ALL_EMPLOYEE_QUERY);
            ps.setInt(1, age);
            ps.setString(2, departmentName);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Manager manager = new Manager();
                manager.setId(resultSet.getInt(1));
                manager.setName(resultSet.getString(2));
                manager.setType(resultSet.getString(5));
                manager.setDepartment(resultSet.getString(7));
                managers.add(manager);
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
        return managers;
    }
}
