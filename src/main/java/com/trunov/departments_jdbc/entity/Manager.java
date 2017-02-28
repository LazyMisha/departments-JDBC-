package com.trunov.departments_jdbc.entity;

import static com.trunov.departments_jdbc.dao.ActionsDao.idEmp;

/**
 * Created by misha on 21.02.17.
 */
public class Manager extends Employee {
    private String methodology;
    public int id;

    public Manager(String name, String lastname, int age, String type, String methodology, String department){
        super(name, lastname, age, type, department);
        this.methodology = methodology;
        id = idEmp;
    }
}
