package com.trunov.departments_jdbc.entity;

/**
 * Created by misha on 21.02.17.
 */
public class Manager extends Employee {
    private String methodology;

    public Manager(String name, String lastName, int age, String type, String methodology, String department){
        super(name, lastName, age, type, department);
        this.methodology = methodology;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }
}
