package com.trunov.departments_jdbc.entity;

/**
 * Created by misha on 21.02.17.
 */
public class Department extends Entity {
    private static int UNIQUE_DEPARTMENT_ID = 0;

    public Department(String name){
        super(name);
        this.id = UNIQUE_DEPARTMENT_ID++;
    }

}
