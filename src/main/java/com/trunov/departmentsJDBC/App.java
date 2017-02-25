package com.trunov.departmentsJDBC;

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
            System.out.println("the app is not correct works!");
            System.out.println("probably you entered the wrong data");
            System.out.println("run app again and type help!");
        }
    }
}
