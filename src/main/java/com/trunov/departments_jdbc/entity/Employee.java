package com.trunov.departments_jdbc.entity;

/**
 * Created by misha on 21.02.17.
 */
public class Employee extends Entity {
    protected static int UNIQUE_EMPLOYEE_ID = 0;

    private int age;
    private String lastname;
    private String type;
    private String department;

    Employee(String name, String lastName, int age, String type, String department) {
        super(name);
        this.lastname = lastName;
        this.age = age;
        this.type = type;
        this.department = department;
        this.id = UNIQUE_EMPLOYEE_ID++;
    }

    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department = department;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

}
