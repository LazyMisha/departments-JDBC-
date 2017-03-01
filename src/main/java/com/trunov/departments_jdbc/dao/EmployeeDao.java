package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by misha on 21.02.17.
 */
public class EmployeeDao {
    private DevelopersDao developersDao = new DevelopersDao();
    private ManagersDao managersDao = new ManagersDao();

    public EmployeeDao() {
        DatabaseUtil.createDatabase();
    }

    public void openEmployeesById(int id) throws SQLException{
        developersDao.openById(id);
        managersDao.openById(id);
    }

    public void openEmployeesByDepartmentName(String departmentName) throws SQLException{
        developersDao.openByDepartmentName(departmentName);
        managersDao.openByDepartmentName(departmentName);
    }

    public void openAll() throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String printAll = "select department, name, type, age from managers " +
                "union all " +
                "select department, name, type, age from developers";
        try {
            connection = DatabaseUtil.getConnection();
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

    public void searchEmployee(int age, String department) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String searchEmployee = "select * from managers where age = " + age + " and department = '" + department + "'" +
                " union all " +
                "select * from developers where age = " + age + " and department = '" + department + "'";
        try {
            connection = DatabaseUtil.getConnection();
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
