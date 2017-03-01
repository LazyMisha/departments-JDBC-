package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.util.DatabaseUtil;
import java.sql.*;

/**
 * Created by misha on 28.02.17.
 */
public class ManagersDao {

    public ManagersDao() {
        DatabaseUtil.createTableManager();
    }

    public void  openById(int id) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String openDeveloper = "select * from managers where id = " + id;
        try{
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(openDeveloper);
            while (resultSet.next()){
                System.out.println("id: " + resultSet.getInt(1) +
                        ", Name: " + resultSet.getString(2) +
                        ", Last Name: " + resultSet.getString(3) +
                        ", Age: " + resultSet.getInt(4) +
                        ", Type: " + resultSet.getString(5) +
                        ", Methodology: " + resultSet.getString(6) +
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

    public void  openByDepartmentName(String departmentName) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String openDeveloper = "select * from managers where department = '" + departmentName + "'";
        try{
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(openDeveloper);
            while (resultSet.next()){
                System.out.println("id: " + resultSet.getInt(1) +
                        ", Name: " + resultSet.getString(2) +
                        ", Last Name: " + resultSet.getString(3) +
                        ", Age: " + resultSet.getInt(4) +
                        ", Type: " + resultSet.getString(5) +
                        ", Methodology: " + resultSet.getString(6) +
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

    public void create(String name, String lastname, int age, char type,
                                     String methodology, String departments) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String createMan = "insert into managers\n" +
                "(name, lastname, age, type, methodology, department)\n" +
                "values\n" +
                "('" + name + "'," + "'" + lastname + "'," + age + "," + "'" + type + "'," +
                "'" + methodology + "'," + "'" + departments + "'" + ")";
        try{
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(createMan);
            preparedStatement.executeUpdate();
            System.out.println("manager is successfully created!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public void  removeById(int managerId) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String deleteMan = "delete from managers where id = " + managerId;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(deleteMan);
            preparedStatement.executeUpdate();
            System.out.println("manager with id " + managerId + " is removed!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public void  updateById(int id, String name, String lastname, int age, char type,
                                         String methodology, String departments) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String updateMan = "update managers set name = '" + name + "', lastname = '" + lastname + "', age = " +
                age + ", type = '" + type + "', methodology = '" + methodology + "', department = '" + departments + "'" +
                " where id = " + id;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(updateMan);
            preparedStatement.executeUpdate();
            System.out.println("manager with id " + id + " is updated!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public void  count() throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String searchMan = "select department, count(*) from managers group by department";
        try{
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchMan);
            resultSet.next();
            System.out.println("Department: " + resultSet.getString(1));
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
