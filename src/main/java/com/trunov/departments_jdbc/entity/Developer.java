package com.trunov.departments_jdbc.entity;

import static com.trunov.departments_jdbc.dao.ActionsDao.idEmp;

/**
 * Created by misha on 21.02.17.
 */
public class Developer extends Employee {
    private String language;
    public int id;

    public Developer(String name, String lastname, int age, String type, String language, String department){
        super(name, lastname, age, type, department);
        this.language = language;
        id = idEmp;
    }
}
