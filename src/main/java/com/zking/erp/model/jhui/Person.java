package com.zking.erp.model.jhui;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Person implements Serializable{
    private String id;

    private String type;

    private String name;

    public Person(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Person() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}