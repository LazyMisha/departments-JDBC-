package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.entity.Manager;
import com.trunov.departments_jdbc.util.DatabaseUtil;
import java.sql.*;

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

    Connection connection = null;

    public ManagersDao() {
        DatabaseUtil.createTableManager();
        connection = DatabaseUtil.getConnection();
    }

    public void  getById(int id){
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SELECT_BY_ID_QUERY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt(1) + "\n" +
                        "Name: " + rs.getString(2) + "\n" + "Last Name: " + rs.getString(3) + "\n" +
                        "Age: " + rs.getInt(4) + "\n" + "Type: " + rs.getString(5) + "\n" +
                        "Methodology: " + rs.getString(6) + "\n" + "Department: " + rs.getString(7));
                System.out.println("<---------------------->");
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
    }

    public void  getByDepartmentName(String departmentName){
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(SELECT_BY_DEPARTMENT_NAME_QUERY);
            ps.setString(1, departmentName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("Id: " + rs.getInt(1) + "\n" +
                        "Name: " + rs.getString(2) + "\n" + "Last Name: " + rs.getString(3) + "\n" +
                        "Age: " + rs.getInt(4) + "\n" + "Type: " + rs.getString(5) + "\n" +
                        "Methodology: " + rs.getString(6) + "\n" + "Department: " + rs.getString(7));
                System.out.println("<---------------------->");
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
    }

    public void save(Manager manager){
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(INSERT_INTO_MANAGERS_QUERY);
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getLastname());
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

    public void  removeById(int managerId){
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

    public void  updateById(int id, String name, String lastname, int age, char type,
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

    public void  count(){
        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_MANAGERS_COUNT_QUERY);
            rs.next();
            System.out.println("Department: " + rs.getString(1) + "\n" +
                    "Count of Managers: " + rs.getInt(2));
        }catch (SQLException e){
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


}
