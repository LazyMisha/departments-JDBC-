package com.trunov.departments_jdbc.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 27.02.17.
 */
public abstract class PrintList {
    public static final List<String> COMMANDS = new ArrayList<>();

    static {
        COMMANDS.add(0, "type \"exit\" for quit from the application");
        COMMANDS.add(1, "type \"getAll -e employee_id\" to watch employee details");
        COMMANDS.add(2, "type \"getAll -d department_name\" to watch employees of this department");
        COMMANDS.add(3, "type \"rm -dv employee_id\" to remove developer");
        COMMANDS.add(4, "type \"rm -mn employee_id\" to remove manager");
        COMMANDS.add(5, "type \"rm -d department_name\" to remove department");
        COMMANDS.add(6, "type \"update -e employee_id -n employee_name -ln employee_lastname " +
                "-t type(m for manager or d for developer) -a age -dp department " +
                "-l language(developers only) -m methodology(manager only)\" to update employee information");
        COMMANDS.add(7, "type \"save -e -n employee_name -ln employee_lastname " +
                "-t type(m for manager or d for developer) -a age -dp department " +
                "-l language(developers only) -m methodology(managers only)\" to save new employee");
        COMMANDS.add(8, "type \"save -d department_name\" to save new department");
        COMMANDS.add(9, "type \"departments\" to return to the list of departments");
        COMMANDS.add(10, "type \"help\" for COMMANDS list");
        COMMANDS.add(11, "type \"all\" for view all employees");
        COMMANDS.add(12, "type \"search -e  -a age_to_search -d department\" for view all employees by age and department name");
        COMMANDS.add(13, "type \"top -d -t type_of_employee\" for view the department with the largest number of employees");
    }
}
