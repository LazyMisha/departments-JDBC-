package com.trunov.departments_jdbc.entity;

/**
 * Created by misha on 21.02.17.
 */
public class Entity {
    protected int id;
    protected String name;

    public Entity(){

    }

    Entity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
