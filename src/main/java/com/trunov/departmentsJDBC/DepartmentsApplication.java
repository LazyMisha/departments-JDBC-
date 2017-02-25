package com.trunov.departmentsJDBC;

import com.trunov.departmentsJDBC.database.Database;
import com.trunov.departmentsJDBC.entity.Departments;
import com.trunov.departmentsJDBC.entity.Developer;
import com.trunov.departmentsJDBC.entity.Manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.trunov.departmentsJDBC.dao.ActionsDao.*;

/**
 * Created by misha on 21.02.17.
 */
public class DepartmentsApplication {

    public void run() throws Exception{
        Database.createDatabase();
        Database.createTableDepartments();
        Database.createTableDevelopers();
        Database.createTableManager();
        System.out.println("ok!");
        System.out.println("please type help for view list of all commands!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            str = reader.readLine();
            if(str.equals("exit")){
                break;
            }else{
                arr = str.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    list.add(arr[i]);
                }
                switch (list.get(0)) {
                    // list of commands
                    case "help":
                        list.clear();
                        printCommandsList();
                        break;
                    // list of departments
                    case "departments":
                        list.clear();
                        openDepartments();
                        break;
                    // open commands
                    case "open":
                        if (list.size() != 3) {
                            System.out.println("not correct command, please type help for vew all commands!");
                            System.out.println("type \"open -e employee_id\" to watch employee details");
                            System.out.println("type \"open -d department_name\" to watch employees of this department");
                            list.clear();
                            break;
                        } else if (list.contains("-e")) {
                            openAllEmployeesById(Integer.parseInt(list.get(2)));
                            list.clear();
                            break;
                        } else if (list.contains("-d")) {
                            openAllEmployeesByDepartmentName(list.get(2));
                            list.clear();
                            break;
                        }
                    // create commands
                    case "create":
                        // create departments
                        if (list.contains("-d")) {
                            new Departments(list.get(2));
                            createDepartments(list.get(2));
                            list.clear();
                            break;
                        }
                        else if (list.size() != 14) {
                            System.out.println("not correct command, please type help for vew all commands!");
                            System.out.println("type \"create -e -n employee_name -t type(m for manager or d for developer) -a age -dp department -l language(developers only) -m methodology(managers only)\" to create new employee");
                            list.clear();
                            break;
                        }else if (list.contains("-e")) {
                            //create developer
                            if (list.contains("d")) {
                                if (list.contains("-m")) {
                                    System.out.println("The developer does not have methodology field");
                                    list.clear();
                                    break;
                                } else {
                                    new Developer(list.get(3), list.get(5),
                                            Integer.parseInt(list.get(9)),
                                            list.get(7), list.get(13), list.get(11));
                                    createDeveloper(list.get(3), list.get(5),
                                            Integer.parseInt(list.get(9)),
                                            list.get(7).charAt(0), list.get(13), list.get(11));
                                    list.clear();
                                    break;
                                }
                            }// create manager
                            else if (list.contains("m")) {
                                if (list.contains("-l")) {
                                    System.out.println("The manager does not have a language field");
                                    list.clear();
                                    break;
                                } else {
                                        new Manager(list.get(3), list.get(5),
                                                Integer.parseInt(list.get(9)),
                                                list.get(7), list.get(13), list.get(11));
                                        createManager(list.get(3), list.get(5),
                                                Integer.parseInt(list.get(9)),
                                                list.get(7).charAt(0), list.get(13), list.get(11));
                                        list.clear();
                                        break;
                                    }
                                }
                            }
                    // remove commands
                    case "rm":
                        if (list.size() != 3) {
                            System.out.println("this command is not correct, please type help for view all commands!");
                            System.out.println("type \"rm -mn employee_id\" to remove manager");
                            System.out.println("type \"rm -dv employee_id\" to remove developer");
                            System.out.println("type \"rm -d department_name\" to remove department");
                            list.clear();
                            break;
                        // remove department
                        } else if (list.contains("-d")){
                            removeDepartmentByName(list.get(2));
                            list.clear();
                            break;
                        // remove developer
                        }else if (list.contains("-dv")) {
                            removeDevelopersById(Integer.parseInt(list.get(2)));
                            list.clear();
                            break;
                        // remove manager
                        }else if (list.contains("-mn")){
                            removeManagerById(Integer.parseInt(list.get(2)));
                            list.clear();
                            break;
                        }
                    // update commands
                    case "update":
                        if (list.size() != 15) {
                            System.out.println("this command is not correct, please type help for view all commands!");
                            System.out.println("type \"update -e employee_id -n employee_name -ln employee_lastname " +
                                    "-t type(m for manager or d for developer) -a age -dp department " +
                                    "-l language(developers only) -m methodology(manager only)\" to update employee information");
                            list.clear();
                            break;
                        }// update developer
                        else if (list.contains("d")) {
                            if (list.contains("-m")) {
                                System.out.println("The developer does not have methodology field");
                                list.clear();
                                break;
                            } else {
                                updateDeveloperById(Integer.parseInt(list.get(2)), list.get(4), list.get(6), Integer.parseInt(list.get(10)),
                                        list.get(8).charAt(0), list.get(14), list.get(12));
                                list.clear();
                                break;
                            }
                        }// update manager
                        else if (list.contains("m")) {
                            if (list.contains("-l")) {
                                System.out.println("The manager does not have a language field");
                                list.clear();
                                break;
                            } else {
                                updateManagerById(Integer.parseInt(list.get(2)), list.get(4), list.get(6), Integer.parseInt(list.get(10)),
                                        list.get(8).charAt(0), list.get(14), list.get(12));
                                list.clear();
                                break;
                            }
                        }
                    // all command
                    case "all":
                        if(list.size() != 1){
                            System.out.println("this command is not correct, please type help for view all commands!");
                            System.out.println("type \"all\" for view all employees");
                            list.clear();
                            break;
                        }else{
                            printAll();
                            list.clear();
                            break;
                        }
                    // search command
                    case "search":
                        if(list.size() != 6){
                            System.out.println("this command is not correct, please type help for view all commands!");
                            System.out.println("type \"search -e  -a age_to_search -d department\" for view all employees by age and department name");
                            list.clear();
                            break;
                        }else{
                            searchEmployee(Integer.parseInt(list.get(3)), list.get(5));
                            list.clear();
                            break;
                        }
                    // top command
                    case "top":
                        if(list.size() != 4){
                            System.out.println("this command is not correct, please type help for view all commands!");
                            System.out.println("type \"top -d -t type_of_employee\" for view the department with the largest number of employees");
                            list.clear();
                            break;
                        }// top department of managers
                        else if(list.contains("m")){
                            countOfManager();
                            list.clear();
                            break;
                        }// top department of developers
                        else if(list.contains("d")){
                            countOfDeveloper();
                            list.clear();
                            break;
                        }
                    default:
                        list.clear();
                        System.out.println("this command is not correct, please type help for view all commands!");
                        break;
                }
            }
        }
    }
}
