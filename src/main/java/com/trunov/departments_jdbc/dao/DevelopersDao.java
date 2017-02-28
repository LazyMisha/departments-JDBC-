package com.trunov.departments_jdbc.dao;

import com.trunov.departments_jdbc.util.Database;

import java.sql.*;

/**
 * Created by misha on 28.02.17.
 */
public abstract class DevelopersDao {

    public static void openById(int id) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String openDeveloper = "select * from developers where id = " + id;
        try{
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(openDeveloper);
            while (resultSet.next()){
                System.out.println("id: " + resultSet.getInt(1) +
                        ", Name: " + resultSet.getString(2) +
                        ", Last Name: " + resultSet.getString(3) +
                        ", Age: " + resultSet.getInt(4) +
                        ", Type: " + resultSet.getString(5) +
                        ", Language: " + resultSet.getString(6) +
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
            if(resultSet!= null){
                resultSet.close();
            }
        }
    }

    public static void openByDepartmentName(String departmentName) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String openDeveloper = "select * from developers where department = '" + departmentName + "'";
        try{
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(openDeveloper);
            while (resultSet.next()){
                System.out.println("id: " + resultSet.getInt(1) +
                        ", Name: " + resultSet.getString(2) +
                        ", Last Name: " + resultSet.getString(3) +
                        ", Age: " + resultSet.getInt(4) +
                        ", Type: " + resultSet.getString(5) +
                        ", Language: " + resultSet.getString(6) +
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
            if(resultSet!= null){
                resultSet.close();
            }
        }
    }

    public static void create(String name, String lastname, int age, char type,
                                       String language, String departments) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String createDev = "insert into developers\n" +
                "(name, lastname, age, type, language, department)\n" +
                "values\n" +
                "('" + name + "'," + "'" + lastname + "'," + age + "," + "'" + type + "'," +
                "'" + language + "'," + "'" + departments + "'" + ")";
        try{
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(createDev);
            preparedStatement.executeUpdate();
            System.out.println("developer is successfully created!");
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

    public static void removeById(int developerId) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String deleteDev = "delete from developers where id =" + developerId;
        try {
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(deleteDev);
            preparedStatement.executeUpdate();
            System.out.println("developer with id " + developerId + " is removed!");
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

    public static void updateById(int id, String name, String lastname, int age, char type,
                                           String language, String departments) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String updateDev = "update developers set name = '" + name + "', lastname = '" + lastname + "', age = " +
                age + ", type = '" + type + "', language = '" + language + "', department = '" + departments + "'" +
                " where id = " + id;
        try {
            connection = Database.connectionToDb();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(updateDev);
            preparedStatement.executeUpdate();
            System.out.println("developer with id " + id + " is updated!");
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

    public static void count() throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet =null;
        String searchMan = "select department, count(*) from developers group by department";
        try{
            connection = Database.connectionToDb();
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
