package com.trunov.departments_jdbc;

/**
 * Start app
 * created 21.02.2017
 */
public class App {
    public static void main( String[] args ) {
        DepartmentsApplication departmentsApplication = new DepartmentsApplication();
        try {
            departmentsApplication.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
