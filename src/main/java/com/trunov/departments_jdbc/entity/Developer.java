package com.trunov.departments_jdbc.entity;

/**
 * Created by misha on 21.02.17.
 */
public class Developer extends Employee {
    private String language;

    public Developer(String name, String lastName, int age, String type, String language, String department){
        super(name, lastName, age, type, department);
        this.language = language;
    }

    public Developer(){

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
