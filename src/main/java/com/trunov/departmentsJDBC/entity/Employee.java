package com.trunov.departmentsJDBC.entity;

/**
 * Created by misha on 21.02.17.
 */
public class Employee extends Entity {
    private String lastname;
    private int age;
    private String type;
    private String department;

    Employee(String name, String lastname, int age, String type, String department) {
        super(name);
        this.lastname = lastname;
        this.age = age;
        this.type = type;
        this.department = department;
    }
}
