package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.entity.Employee;
import com.trunov.departments_jdbc.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 21.02.17.
 */
public class EmployeeDao {

    private static final String SELECT_ALL_QUERY = "select department, name, type, age from managers " +
            "union " +
            "select department, name, type, age from developers";

    Connection connection = null;

    public EmployeeDao() {
        DatabaseUtil.createDatabase();
        connection = DatabaseUtil.getConnection();
    }

    public List<Employee> getAll(){
        Statement statement = null;
        List<Employee> employees = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()){
                Employee employee = new Employee();
                employee.setDepartment(rs.getString(1));
                employee.setName(rs.getString(2));
                employee.setType(rs.getString(3));
                employee.setAge(rs.getInt(4));
                employees.add(employee);
            }
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
        return employees;
    }
}
