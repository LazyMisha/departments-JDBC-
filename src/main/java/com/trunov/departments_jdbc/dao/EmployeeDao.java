package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.entity.Developer;
import com.trunov.departments_jdbc.util.DatabaseUtil;

import javax.xml.bind.ValidationEvent;
import java.sql.*;

/**
 * Created by misha on 21.02.17.
 */
public class EmployeeDao {

    private static final String SELECT_ALL_QUERY = "select department, name, type, age from managers " +
            "union all " +
            "select department, name, type, age from developers";
    private static final String SEARCH_ALL_EMPLOYEE_QUERY = "select * from managers where age = ? and department = ?" +
            " union all " +
            "select * from developers where age = ? and department = ?";

    Connection connection = null;

    ManagersDao managersDao = new ManagersDao();
    DevelopersDao developersDao = new DevelopersDao();

    public EmployeeDao() {
        DatabaseUtil.createDatabase();
        connection = DatabaseUtil.getConnection();
    }

    public void getAllEmployeesById(int id){
        developersDao.getById(id);
        managersDao.getById(id);
    }

    public void getEmployeesByDepartmentName(String departmentName){
        developersDao.getByDepartmentName(departmentName);
        managersDao.getByDepartmentName(departmentName);
    }

    public void getAll(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while(rs.next()){
                System.out.println("Department Name: " + rs.getString(1) + "\n" +
                        "Employee Name: " + rs.getString(2) + "\n" +
                "Employee Type: " + rs.getString(3) + "\n" +
                "Employee Age: " + rs.getInt(4));
                System.out.println("<---------------------->");
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
    }

    public void searchEmployee(int age, String department){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SEARCH_ALL_EMPLOYEE_QUERY);
            ps.setInt(1, age);
            ps.setString(2, department);
            ps.setInt(3, age);
            ps.setString(4, department);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                System.out.println("Id: " + resultSet.getInt(1) + "\n" +
                        "Name: " + resultSet.getString(2) + "\n" +
                        "Type: " + resultSet.getString(5) + "\n" +
                        "Department: " + resultSet.getString(7));
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

}
