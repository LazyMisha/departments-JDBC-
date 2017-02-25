package com.trunov.departmentsJDBC.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by misha on 21.02.17.
 */
public class ActionsDao {
    public static int idEmp;
    public static String str;
    public static ArrayList<String> list = new ArrayList<>();
    public static String arr[];
    public static String Url = "jdbc:mysql://localhost/departments?autoReconnect=true&useSSL=false";
    public static String Driver = "com.mysql.jdbc.Driver";
    public static String User = "root";
    public static String Password = "mysql";


    public static void printCommandsList(){
        LinkedList<String> commands = new LinkedList<>();
        commands.add(0, "type \"exit\" for quit from the application");
        commands.add(1, "type \"open -e employee_id\" to watch employee details");
        commands.add(2, "type \"open -d department_name\" to watch employees of this department");
        commands.add(3, "type \"rm -dv employee_id\" to remove developer");
        commands.add(13, "type \"rm -mn employee_id\" to remove manager");
        commands.add(4, "type \"rm -d department_name\" to remove department");
        commands.add(5, "type \"update -e employee_id -n employee_name -ln employee_lastname " +
                "-t type(m for manager or d for developer) -a age -dp department " +
                "-l language(developers only) -m methodology(manager only)\" to update employee information");
        commands.add(6, "type \"create -e -n employee_name -ln employee_lastname " +
                "-t type(m for manager or d for developer) -a age -dp department " +
                "-l language(developers only) -m methodology(managers only)\" to create new employee");
        commands.add(7, "type \"create -d department_name\" to create new department");
        commands.add(8, "type \"departments\" to return to the list of departments");
        commands.add(9, "type \"help\" for commands list");
        commands.add(10, "type \"all\" for view all employees");
        commands.add(11, "type \"search -e  -a age_to_search -d department\" for view all employees by age and department name");
        commands.add(12, "type \"top -d -t type_of_employee\" for view the department with the largest number of employees");
        for(int i = 0; i < commands.size(); i++) {
            System.out.println(commands.get(i));
        }
    }

    public static void openDepartments(){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String sqlDepartments = "SELECT * FROM departments";
            ResultSet departments = statement.executeQuery(sqlDepartments);
            while(departments.next()){
                System.out.println("id: " + departments.getInt(1) +
                        ", Name: " + departments.getString(2) + ".");
            }
            connection.close();
            statement.close();
            departments.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void openDevelopersById(int id){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String openDeveloper = "select * from developers where id = " + id;
            ResultSet developer = statement.executeQuery(openDeveloper);
            while (developer.next()){
                System.out.println("id: " + developer.getInt(1) +
                ", Name: " + developer.getString(2) +
                ", Last Name: " + developer.getString(3) +
                ", Age: " + developer.getInt(4) +
                ", Type: " + developer.getString(5) +
                ", Language: " + developer.getString(6) +
                ", Department: " + developer.getString(7));
            }
            connection.close();
            statement.close();
            developer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void openManagersById(int id){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String openDeveloper = "select * from managers where id = " + id;
            ResultSet manager = statement.executeQuery(openDeveloper);
            while (manager.next()){
                System.out.println("id: " + manager.getInt(1) +
                        ", Name: " + manager.getString(2) +
                        ", Last Name: " + manager.getString(3) +
                        ", Age: " + manager.getInt(4) +
                        ", Type: " + manager.getString(5) +
                        ", Methodology: " + manager.getString(6) +
                        ", Department: " + manager.getString(7));
            }
            connection.close();
            statement.close();
            manager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void openAllEmployeesById(int id){
        openDevelopersById(id);
        openManagersById(id);
    }

    public static void openDeveloperByDepartmentName(String departmentName){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String openDeveloper = "select * from developers where department = '" + departmentName + "'";
            ResultSet developer = statement.executeQuery(openDeveloper);
            while (developer.next()){
                System.out.println("id: " + developer.getInt(1) +
                        ", Name: " + developer.getString(2) +
                        ", Last Name: " + developer.getString(3) +
                        ", Age: " + developer.getInt(4) +
                        ", Type: " + developer.getString(5) +
                        ", Language: " + developer.getString(6) +
                        ", Department: " + developer.getString(7));
            }
            connection.close();
            statement.close();
            developer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void openManagerByDepartmentName(String departmentName){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String openDeveloper = "select * from managers where department = '" + departmentName + "'";
            ResultSet manager = statement.executeQuery(openDeveloper);
            while (manager.next()){
                System.out.println("id: " + manager.getInt(1) +
                        ", Name: " + manager.getString(2) +
                        ", Last Name: " + manager.getString(3) +
                        ", Age: " + manager.getInt(4) +
                        ", Type: " + manager.getString(5) +
                        ", Methodology: " + manager.getString(6) +
                        ", Department: " + manager.getString(7));
            }
            connection.close();
            statement.close();
            manager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void openAllEmployeesByDepartmentName(String departmentName){
        openDeveloperByDepartmentName(departmentName);
        openManagerByDepartmentName(departmentName);
    }

    public static void createDepartments(String departmentName){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String createDep = "insert into departments (departments)\n" +
                    "values ('" + departmentName + "')";
            PreparedStatement ps = connection.prepareStatement(createDep);
            ps.executeUpdate();

            System.out.println("department is successfully created!");

            connection.close();
            statement.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void createDeveloper(String name, String lastname, int age, char type,
                                       String language, String departments){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String createDev = "insert into developers\n" +
                    "(name, lastname, age, type, language, department)\n" +
                    "values\n" +
                    "('" + name + "'," + "'" + lastname + "'," + age + "," + "'" + type + "'," +
                    "'" + language + "'," + "'" + departments + "'" + ")";
            PreparedStatement ps = connection.prepareStatement(createDev);
            ps.executeUpdate();

            System.out.println("developer is successfully created!");

            connection.close();
            statement.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void createManager(String name, String lastname, int age, char type,
                                     String methodology, String departments){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String createMan = "insert into managers\n" +
                    "(name, lastname, age, type, methodology, department)\n" +
                    "values\n" +
                    "('" + name + "'," + "'" + lastname + "'," + age + "," + "'" + type + "'," +
                    "'" + methodology + "'," + "'" + departments + "'" + ")";
            PreparedStatement ps = connection.prepareStatement(createMan);
            ps.executeUpdate();

            System.out.println("manager is successfully created!");

            connection.close();
            statement.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removeDepartmentByName(String departmentName){
        try {
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String deleteDep = "delete from departments where departments = '" + departmentName + "'";
            PreparedStatement ps = connection.prepareStatement(deleteDep);
            ps.executeUpdate();

            System.out.println("department " + departmentName + " is removed!");

            connection.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removeDevelopersById(int developerId){
        try {
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String deleteDev = "delete from developers where id =" + developerId;
            PreparedStatement ps = connection.prepareStatement(deleteDev);
            ps.executeUpdate();

            System.out.println("developer with id " + developerId + " is removed!");

            connection.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removeManagerById(int managerId){
        try {
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String deleteMan = "delete from managers where id = " + managerId;
            PreparedStatement ps = connection.prepareStatement(deleteMan);
            ps.executeUpdate();

            System.out.println("manager with id " + managerId + " is removed!");

            connection.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateDeveloperById(int id, String name, String lastname, int age, char type,
                                           String language, String departments){
        try {
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String updateDev = "update developers set name = '" + name + "', lastname = '" + lastname + "', age = " +
                    age + ", type = '" + type + "', language = '" + language + "', department = '" + departments + "'" +
                    " where id = " + id;
            PreparedStatement ps = connection.prepareStatement(updateDev);
            ps.executeUpdate();

            System.out.println("developer with id " + id + " is updated!");

            connection.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateManagerById(int id, String name, String lastname, int age, char type,
                                         String methodology, String departments){
        try {
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String updateMan = "update managers set name = '" + name + "', lastname = '" + lastname + "', age = " +
                    age + ", type = '" + type + "', methodology = '" + methodology + "', department = '" + departments + "'" +
                    " where id = " + id;
            PreparedStatement ps = connection.prepareStatement(updateMan);
            ps.executeUpdate();

            System.out.println("manager with id " + id + " is updated!");

            connection.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void printAll(){
        try {
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String printAll = "select department, name, type, age from managers " +
                    "union all " +
                    "select department, name, type, age from developers";
            ResultSet all = statement.executeQuery(printAll);

            while(all.next()){
                System.out.println("Department Name: " + all.getString(1) +
                        ", Employee Name: " + all.getString(2) +
                ", Employee Type: " + all.getString(3) +
                ", Employee Age: " + all.getInt(4));
            }

            connection.close();
            statement.close();
            all.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void searchEmployee(int age, String department){
        try {
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String searchEmployee = "select * from managers where age = " + age + " and department = '" + department + "'" +
                    " union all " +
                    "select * from developers where age = " + age + " and department = '" + department + "'";
            ResultSet search = statement.executeQuery(searchEmployee);

            while (search.next()){
                System.out.println("id: " + search.getInt(1) +
                        ", Name: " + search.getString(2) +
                        ", Type: " + search.getString(5) +
                        ", Department: " + search.getString(7));
            }

            connection.close();
            statement.close();
            search.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void countOfManager(){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String searchMan = "select department, count(*) from managers group by department";
            ResultSet search = statement.executeQuery(searchMan);

            search.next();
            System.out.println("Department: " + search.getString(1));

            connection.close();
            statement.close();
            search.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void countOfDeveloper(){
        try{
            Class.forName(Driver);
            Connection connection = DriverManager.getConnection(Url, User, Password);
            Statement statement = connection.createStatement();
            String searchMan = "select department, count(*) from developers group by department";
            ResultSet search = statement.executeQuery(searchMan);

            search.next();
            System.out.println("Department: " + search.getString(1));

            connection.close();
            statement.close();
            search.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
