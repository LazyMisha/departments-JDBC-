package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.entity.Department;
import com.trunov.departments_jdbc.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 28.02.17.
 */
public class DepartmentsDao {
    private static final String SELECT_ALL_QUERY = "select * from departments";
    private static final String INSERT_QUERY = "insert into departments (id, departments) values (?,?)";
    private static final String DELETE_BY_NAME_QUERY = "delete from departments where departments = ?";

    private Connection connection = null;

    public DepartmentsDao() {
        DatabaseUtil.createTableDepartments();
        connection = DatabaseUtil.getConnection();
    }

    public List<Department> getAll(){
        Statement statement = null;
        List<Department> departments = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            if (rs.next()) {
                Department department = new Department(rs.getString(2));
                department.setId(rs.getInt(1));
                department.setName(rs.getString(2));
                departments.add(department);
            }
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
        return departments;
    }

    public void save(Department department) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(INSERT_QUERY);
            ps.setInt(1, department.getId());
            ps.setString(2, department.getName());
            ps.executeUpdate();

            System.out.println("department is successfully created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void removeByName(String departmentName) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(DELETE_BY_NAME_QUERY);
            ps.setString(1, departmentName);
            ps.executeUpdate();
            System.out.println("department " + departmentName + " is removed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}