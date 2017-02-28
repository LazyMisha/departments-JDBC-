package com.trunov.departments_jdbc.util;

import java.util.LinkedList;

/**
 * Created by misha on 27.02.17.
 */
public abstract class PrintList {

    public static LinkedList printCommandsList(){
        LinkedList<String> commands = new LinkedList<>();
        commands.add(0, "type \"exit\" for quit from the application");
        commands.add(1, "type \"open -e employee_id\" to watch employee details");
        commands.add(2, "type \"open -d department_name\" to watch employees of this department");
        commands.add(3, "type \"rm -dv employee_id\" to remove developer");
        commands.add(4, "type \"rm -mn employee_id\" to remove manager");
        commands.add(5, "type \"rm -d department_name\" to remove department");
        commands.add(6, "type \"update -e employee_id -n employee_name -ln employee_lastname " +
                "-t type(m for manager or d for developer) -a age -dp department " +
                "-l language(developers only) -m methodology(manager only)\" to update employee information");
        commands.add(7, "type \"create -e -n employee_name -ln employee_lastname " +
                "-t type(m for manager or d for developer) -a age -dp department " +
                "-l language(developers only) -m methodology(managers only)\" to create new employee");
        commands.add(8, "type \"create -d department_name\" to create new department");
        commands.add(9, "type \"departments\" to return to the list of departments");
        commands.add(10, "type \"help\" for commands list");
        commands.add(11, "type \"all\" for view all employees");
        commands.add(12, "type \"search -e  -a age_to_search -d department\" for view all employees by age and department name");
        commands.add(13, "type \"top -d -t type_of_employee\" for view the department with the largest number of employees");
        return commands;
    }
}
