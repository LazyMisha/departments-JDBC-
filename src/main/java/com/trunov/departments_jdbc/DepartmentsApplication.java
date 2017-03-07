package com.trunov.departments_jdbc;

import com.trunov.departments_jdbc.dao.DepartmentsDao;
import com.trunov.departments_jdbc.dao.DevelopersDao;
import com.trunov.departments_jdbc.dao.EmployeeDao;
import com.trunov.departments_jdbc.dao.ManagersDao;
import com.trunov.departments_jdbc.entity.Department;
import com.trunov.departments_jdbc.entity.Developer;
import com.trunov.departments_jdbc.entity.Employee;
import com.trunov.departments_jdbc.entity.Manager;
import com.trunov.departments_jdbc.util.DatabaseUtil;
import com.trunov.departments_jdbc.util.PrintList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by misha on 21.02.17.
 */
public class DepartmentsApplication {
    private String str;
    private String arr[];
    private ArrayList<String> list = new ArrayList<>();

    private EmployeeDao employeeDao = new EmployeeDao();
    private DepartmentsDao departmentsDao = new DepartmentsDao();
    private ManagersDao managersDao = new ManagersDao();
    private DevelopersDao developersDao = new DevelopersDao();

    public void run() throws SQLException {
        System.out.println("please type help for view list of all COMMANDS!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                str = reader.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (str.equals("exit")) {
                DatabaseUtil.closeConnection();
                break;
            } else {
                arr = str.split(" ");
                for (String anArr : arr) {
                    list.add(anArr);
                }
                switch (list.get(0)) {
                    // list of COMMANDS
                    case "help":
                        list.clear();
                        for (String command : PrintList.COMMANDS) {
                            System.out.println(command);
                        }
                        break;
                    // list of departments
                    case "departments":
                        for (Department department : departmentsDao.getAll()) {
                            System.out.println("id: " + department.getId() +
                                    ", Name: " + department.getName() + ".");
                        }
                        list.clear();
                        break;
                    // getAll COMMANDS
                   case "getAll":
                        if (list.size() != 3) {
                            System.out.println("not correct command, please type help for vew all COMMANDS!");
                            System.out.println("type \"getAll -e employee_id\" to watch employee details");
                            System.out.println("type \"getAll -d department_name\" to watch employees of this department");
                            list.clear();
                            break;
                        } else if (list.contains("-e")) {
                            System.out.println("Developer with id " + list.get(2));
                            for (Developer developer : developersDao.getById(Integer.parseInt(list.get(2)))) {
                                System.out.println("id: " + developer.getId() +
                                        ", Name: " + developer.getName() +
                                        ", Last Name: " + developer.getLastname() +
                                        ", Age: " + developer.getAge() +
                                        ", Type: " + developer.getType() +
                                        ", Language: " + developer.getLanguage() +
                                        ", Department: " + developer.getDepartment());
                            }
                            System.out.println("Manager with id " + list.get(2));
                            for (Manager manager : managersDao.getById(Integer.parseInt(list.get(2)))) {
                                System.out.println("id: " + manager.getId() +
                                        ", Name: " + manager.getName() +
                                        ", Last Name: " + manager.getLastname() +
                                        ", Age: " + manager.getAge() +
                                        ", Type: " + manager.getType() +
                                        ", Language: " + manager.getMethodology() +
                                        ", Department: " + manager.getDepartment());
                            }
                            list.clear();
                            break;
                        } else if (list.contains("-d")) {
                            System.out.println("All developers from department " + list.get(2));
                            for (Developer developer : developersDao.getByDepartmentName(list.get(2))) {
                                System.out.println("id: " + developer.getId() +
                                        ", Name: " + developer.getName() +
                                        ", Last Name: " + developer.getLastname() +
                                        ", Age: " + developer.getAge() +
                                        ", Type: " + developer.getType() +
                                        ", Language: " + developer.getLanguage() +
                                        ", Department: " + developer.getDepartment());
                            }
                            System.out.println("All managers from department " + list.get(2));
                            for (Manager manager : managersDao.getByDepartmentName(list.get(2))) {
                                System.out.println("id: " + manager.getId() +
                                        ", Name: " + manager.getName() +
                                        ", Last Name: " + manager.getLastname() +
                                        ", Age: " + manager.getAge() +
                                        ", Type: " + manager.getType() +
                                        ", Language: " + manager.getMethodology() +
                                        ", Department: " + manager.getDepartment());
                            }
                            list.clear();
                            break;
                        }
                        // save COMMANDS
                    case "save":
                        // save departments
                        if (list.contains("-d")) {
                            Department department = new Department(list.get(2));
                            departmentsDao.save(department);
                            list.clear();
                            break;
                        } else if (list.size() != 14) {
                            System.out.println("not correct command, please type help for vew all COMMANDS!");
                            System.out.println("type \"save -e -n employee_name -t type(m for manager or d for developer) " +
                                    "-a age -dp department -l language(developers only) -m methodology(managers only)\" to save new employee");
                            list.clear();
                            break;
                        } else if (list.contains("-e")) {
                            //save developer
                            if (list.contains("d")) {
                                if (list.contains("-m")) {
                                    System.out.println("The developer does not have methodology field");
                                    list.clear();
                                    break;
                                } else {
                                    Developer developer = new Developer(list.get(3), list.get(5),
                                            Integer.parseInt(list.get(9)),
                                            list.get(7), list.get(13), list.get(11));
                                    developersDao.save(developer);
                                    list.clear();
                                    break;
                                }
                            }// save manager
                            else if (list.contains("m")) {
                                if (list.contains("-l")) {
                                    System.out.println("The manager does not have a language field");
                                    list.clear();
                                    break;
                                } else {
                                    Manager manager = new Manager(list.get(3), list.get(5),
                                            Integer.parseInt(list.get(9)),
                                            list.get(7), list.get(13), list.get(11));
                                    managersDao.save(manager);
                                    list.clear();
                                    break;
                                }
                            }
                        }
                        // remove COMMANDS
                    case "rm":
                        if (list.size() != 3) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
                            System.out.println("type \"rm -mn employee_id\" to remove manager");
                            System.out.println("type \"rm -dv employee_id\" to remove developer");
                            System.out.println("type \"rm -d department_name\" to remove department");
                            list.clear();
                            break;
                            // remove department
                        } else if (list.contains("-d")) {
                            departmentsDao.removeByName(list.get(2));
                            list.clear();
                            break;
                            // remove developer
                        } else if (list.contains("-dv")) {
                            developersDao.removeById(Integer.parseInt(list.get(2)));
                            list.clear();
                            break;
                            // remove manager
                        } else if (list.contains("-mn")) {
                            managersDao.removeById(Integer.parseInt(list.get(2)));
                            list.clear();
                            break;
                        }
                        // update COMMANDS
                    case "update":
                        if (list.size() != 15) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
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
                                developersDao.updateById(Integer.parseInt(list.get(2)), list.get(4), list.get(6), Integer.parseInt(list.get(10)),
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
                                managersDao.updateById(Integer.parseInt(list.get(2)), list.get(4), list.get(6), Integer.parseInt(list.get(10)),
                                        list.get(8).charAt(0), list.get(14), list.get(12));
                                list.clear();
                                break;
                            }
                        }
                        // all command
                    case "all":
                        if (list.size() != 1) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
                            System.out.println("type \"all\" for view all employees");
                            list.clear();
                            break;
                        } else {
                            for (Employee employee : employeeDao.getAll()) {
                                System.out.println("Department: " + employee.getDepartment() + "\n" +
                                        "Name: " + employee.getName() + "\n" +
                                        "Type: " + employee.getType() + "\n" +
                                        "Age: " + employee.getAge() + "\n");
                            }
                            list.clear();
                            break;
                        }
                        // search command
                    case "search":
                        if (list.size() != 6) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
                            System.out.println("type \"search -e  -a age_to_search -d department\" for view all employees by age and department name");
                            list.clear();
                            break;
                        } else {
                            System.out.println("Developer with id " + list.get(3)+ ", and department " + list.get(5) + ":" + "\n");
                            for(Developer developer : developersDao.searchEmployee(Integer.parseInt(list.get(3)), list.get(5))){
                                System.out.println("Id: " + developer.getId() + "\n" +
                                        "Name: " + developer.getName() + "\n" +
                                        "Type: " + developer.getType() + "\n" +
                                        "Department: " + developer.getDepartment() + "\n");
                            }
                            System.out.println("Manager with id " + list.get(3)+ ", and department " + list.get(5) + ":" + "\n");
                            for(Manager manager : managersDao.searchEmployee(Integer.parseInt(list.get(3)), list.get(5))){
                                System.out.println("Id: " + manager.getId() + "\n" +
                                        "Name: " + manager.getName() + "\n" +
                                        "Type: " + manager.getType() + "\n" +
                                        "Department: " + manager.getDepartment() + "\n");
                            }
                            list.clear();
                            break;
                        }
                        // top command
                    case "top":
                        if (list.size() != 4) {
                            System.out.println("this command is not correct, please type help for view all COMMANDS!");
                            System.out.println("type \"top -d -t type_of_employee\" for view the department with the largest number of employees");
                            list.clear();
                            break;
                        }// top department of managers
                        else if (list.contains("d")) {
                                System.out.println("Department: " + developersDao.count().get(0) + "\n" +
                                "Number of developers: " + developersDao.count().get(1));
                            list.clear();
                            break;
                        }// top department of developers
                        else if (list.contains("m")) {
                            System.out.println("Department: " + managersDao.count().get(0) + "\n" +
                                    "Number of managers: " + managersDao.count().get(1));
                            list.clear();
                            break;
                        }
                    default:
                        list.clear();
                        System.out.println("this command is not correct, please type help for view all COMMANDS!");
                        break;
                }
            }
        }
    }
}
